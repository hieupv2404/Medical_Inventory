package com.java1906.climan.controller;

import com.java1906.climan.data.model.*;
import com.java1906.climan.data.repo.InvoiceSupplierReportRepository;
import com.java1906.climan.data.repo.InvoiceSupplierRepository;
import com.java1906.climan.dto.InvoiceDTO;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.IInvoiceItemService;
import com.java1906.climan.services.IInvoiceService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class InvoiceImportController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IInvoiceItemService invoiceItemService;

    @Autowired
    private InvoiceSupplierRepository invoiceSupplierRepository;

    @Autowired
    private InvoiceSupplierReportRepository invoiceSupplierReportRepository;


    double priceInTotal = 0;
    double priceOutTotal = 0;

    @GetMapping("/invoiceImport")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<List<InvoiceSupplier>> showInvoiceList(Model model,@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "code") String code,
                                                                 @RequestParam("email") String email) {
        invoiceSupplierRepository.deleteAll();
        List<InvoiceSupplier> invoiceImport = invoiceService.findAllInvoiceImport(name, code, email);
        priceInTotal = 0;
        priceOutTotal = 0;
        for (InvoiceSupplier invoiceImport1:invoiceImport)
        {
            priceInTotal +=invoiceImport1.getInTotal();
            priceOutTotal += invoiceImport1.getOutTotal();
            InvoiceSupplierReport invoiceSupplierReport = new InvoiceSupplierReport();
            invoiceSupplierReport.setCreatedDate(invoiceImport1.getCreatedDate());
            invoiceSupplierReport.setEmail(invoiceImport1.getEmail());
            invoiceSupplierReport.setCode(invoiceImport1.getCode());
            invoiceSupplierReport.setSupplierId(invoiceImport1.getSupplierId());
            invoiceSupplierReport.setSupplierName(invoiceImport1.getSupplierName());
            invoiceSupplierReport.setSupplierAddress(invoiceImport1.getSupplierAddress());
            invoiceSupplierReport.setInTotal(invoiceImport1.getInTotal());
            invoiceSupplierReport.setOutTotal(invoiceImport1.getOutTotal());
            invoiceSupplierReport.setNumberPhone(invoiceImport1.getNumberPhone());
            invoiceSupplierReport.setUpdatedDate(invoiceImport1.getUpdatedDate());
            invoiceSupplierReport.setId(invoiceImport1.getId());
            invoiceSupplierReportRepository.save(invoiceSupplierReport);
        }
        model.addAttribute("titlePage","Invoice Export");
        model.addAttribute("priceInTotal",priceInTotal);
        model.addAttribute("priceOutTotal",priceOutTotal);
        model.addAttribute("InvoiceCustomerList",invoiceImport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get  by id
    @GetMapping("/invoiceImport/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<Object> getInvoiceById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(invoiceService.findById(id),HttpStatus.OK);
    }
  // create invoice
    @PostMapping("/invoiceImport/")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> createInvoce(@RequestBody Invoice invoice){
        invoice.setType(1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int maxId = invoiceSupplierRepository.findMaxIdInInvoice();
        invoice.setCode("Import"+( String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) ) + (String.valueOf(calendar.get(Calendar.MONTH))) + String.valueOf(maxId) ));
        return new ResponseEntity<>(invoiceService.save(invoice),HttpStatus.CREATED);
    }

    //update invoice
    @PutMapping("/invoiceImport/{invoiceId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("invoiceId") int invoiceId, @RequestBody Invoice invoice){
        return new ResponseEntity<>(invoiceService.update(invoiceId,invoice),HttpStatus.CREATED);
    }
    //
    @DeleteMapping("/{invoiceImportId}")
    public ResponseEntity<String> deleteInvoice( @PathVariable("invoiceImportId") int invoiceId){
      invoiceService.delete(invoiceId);
        return new ResponseEntity<>("Delete Ok",HttpStatus.OK);
    }

    @GetMapping("/invoiceImport/report")
    public ResponseEntity<String> reportForInvoiceExport() throws IOException {

        List<InvoiceSupplierReport> invoiceSupplierReports = invoiceSupplierReportRepository.findInvoiceImport();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Invoice Export sheet");
        int rowNum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rowNum);

        // col Id invoice header
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ID Invoice");

        // col code invoice header
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Code");

        // col name supplier
        cell = row.createCell(2,CellType.STRING);
        cell.setCellValue("Supplier");

        // col address supplier
        cell = row.createCell(3,CellType.STRING);
        cell.setCellValue("Address");

        // col phone supplier
        cell = row.createCell(4,CellType.STRING);
        cell.setCellValue("Phone");

        // col email supplier
        cell = row.createCell(5,CellType.STRING);
        cell.setCellValue("Email");

        // col in total inv
        cell = row.createCell(6,CellType.STRING);
        cell.setCellValue("In Total");

        // col out total inv
        cell = row.createCell(7,CellType.STRING);
        cell.setCellValue("Out Total");

        // col create date inv
        cell = row.createCell(8,CellType.STRING);
        cell.setCellValue("Create Date");

        // col update date inv
        cell = row.createCell(9,CellType.STRING);
        cell.setCellValue("Update Date");

        // Data

        for(InvoiceSupplierReport invoiceSupplierReports1 : invoiceSupplierReports)
        {
            rowNum++;
            row = sheet.createRow(rowNum);

            // col Id invoice header
            cell = row.createCell(0,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getId());

            // col code invoice header
            cell = row.createCell(1,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getCode());

            // col name supplier
            cell = row.createCell(2,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getSupplierName());

            // col address supplier
            cell = row.createCell(3,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getSupplierAddress());

            // col phone supplier
            cell = row.createCell(4,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getNumberPhone());

            // col email cus
            cell = row.createCell(5,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getEmail());

            // col in total inv
            cell = row.createCell(6,CellType.NUMERIC);
            cell.setCellValue(invoiceSupplierReports1.getInTotal());

            // col out total inv
            cell = row.createCell(7,CellType.NUMERIC);
            cell.setCellValue(invoiceSupplierReports1.getOutTotal());

            // col create date inv
            cell = row.createCell(8,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getCreatedDate());

            // col update date inv
            cell = row.createCell(9,CellType.STRING);
            cell.setCellValue(invoiceSupplierReports1.getUpdatedDate());


            //end data
        }

        rowNum++;
        row = sheet.createRow(rowNum);

        // col create date inv
        cell = row.createCell(5,CellType.NUMERIC);
        cell.setCellValue("Total");

        // col create date inv
        cell = row.createCell(6,CellType.NUMERIC);
        cell.setCellValue(priceInTotal);

        // col create date inv
        cell = row.createCell(7,CellType.STRING);
        cell.setCellValue(priceOutTotal);


        // export file
        File file = new File("E:\\Do An\\T3H\\InvoiceImport.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
