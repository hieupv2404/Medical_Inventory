package com.java1906.climan.controller;

import com.java1906.climan.data.model.*;
import com.java1906.climan.data.repo.InvoiceCustomerReportRepository;
import com.java1906.climan.data.repo.InvoiceCustomerRepository;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.IInvoiceExportService;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class InvoiceExportController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IInvoiceExportService invoiceExportService;

    @Autowired
    private InvoiceCustomerReportRepository invoiceCustomerReportRepository;

    @Autowired
    private InvoiceCustomerRepository invoiceCustomerRepository;

    double priceInTotal = 0;
    double priceOutTotal = 0;

    @GetMapping("/invoices-export")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<List<InvoiceCustomer>> showInvoiceList(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "code") String code,
                                                                 @RequestParam("email") String email) {
        invoiceCustomerReportRepository.deleteAll();
        priceInTotal = 0;
        priceOutTotal = 0;
        List<InvoiceCustomer> invoiceExport = invoiceService.findAllInvoiceExport(name, code, email);

        for (InvoiceCustomer invoiceExport1:invoiceExport)
        {
            priceInTotal +=invoiceExport1.getInTotal();
            priceOutTotal += invoiceExport1.getOutTotal();
            InvoiceCustomerReport invoiceCustomerReport = new InvoiceCustomerReport();
            invoiceCustomerReport.setAddress(invoiceExport1.getAddress());
            invoiceCustomerReport.setCode(invoiceExport1.getCode());
            invoiceCustomerReport.setCreatedDate(invoiceExport1.getCreatedDate());
            invoiceCustomerReport.setEmail(invoiceExport1.getEmail());
            invoiceCustomerReport.setCustomerId(invoiceExport1.getCustomerId());
            invoiceCustomerReport.setName(invoiceExport1.getName());
            invoiceCustomerReport.setInTotal(invoiceExport1.getInTotal());
            invoiceCustomerReport.setOutTotal(invoiceExport1.getOutTotal());
            invoiceCustomerReport.setNumberPhone(invoiceExport1.getNumberPhone());
            invoiceCustomerReport.setUpdatedDate(invoiceExport1.getUpdatedDate());
            invoiceCustomerReport.setId(invoiceExport1.getId());
            invoiceCustomerReportRepository.save(invoiceCustomerReport);
        }

        return new ResponseEntity<>(invoiceExport,HttpStatus.OK);
    }

    //get  by id
    @GetMapping("/invoices-export/{id}")
    @HasRole({"STAFF", "ADMIN"})
    public ResponseEntity<InvoiceCustomer> getInvoiceById(@PathVariable("id") Integer id) {
        InvoiceCustomer invoiceExport = invoiceService.findExportById(id);
        if (invoiceExport == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoiceExport, HttpStatus.OK);
    }

  // create invoice
    @PostMapping("/invoices-export/")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice){
        invoice.setType(2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int maxId = invoiceCustomerRepository.findMaxIdInInvoice();
        invoice.setCode("Export"+( String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) ) + (String.valueOf(calendar.get(Calendar.MONTH))) + String.valueOf(maxId) ));
        return new ResponseEntity<>(invoiceService.save(invoice),HttpStatus.CREATED);
    }

    //update invoice
    @PutMapping("/invoices-export/{invoiceId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("invoiceId") int invoiceId, @RequestBody Invoice invoice){
        return new ResponseEntity<>(invoiceService.update(invoiceId,invoice),HttpStatus.CREATED);
    }
    //
    @DeleteMapping("/{invoiceExportId}")
    public ResponseEntity<String> deleteInvoice( @PathVariable("invoiceExportId") int invoiceId){
      invoiceService.delete(invoiceId);
        return new ResponseEntity<>("Delete Ok",HttpStatus.OK);
    }

    @GetMapping("/invoices-export/report")
    public ResponseEntity<String> reportForInvoiceExport() throws IOException {

        List<InvoiceCustomerReport> invoiceCustomerReports = invoiceCustomerReportRepository.findInvoiceExport();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Invoice Export sheet");
        int rowNum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rowNum);

        // col Id invoice header
        cell = row.createCell(0,CellType.STRING);
        cell.setCellValue("ID Invoice");

        // col code invoice header
        cell = row.createCell(1,CellType.STRING);
        cell.setCellValue("Code");

        // col name customer
        cell = row.createCell(2,CellType.STRING);
        cell.setCellValue("Customer");

        // col address cus
        cell = row.createCell(3,CellType.STRING);
        cell.setCellValue("Address");

        // col phone cus
        cell = row.createCell(4,CellType.STRING);
        cell.setCellValue("Phone");

        // col email cus
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

        for(InvoiceCustomerReport invoiceCustomerReport : invoiceCustomerReports)
        {
            rowNum++;
            row = sheet.createRow(rowNum);

            // col Id invoice header
            cell = row.createCell(0,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getId());

            // col Id invoice header
            cell = row.createCell(1,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getCode());

            // col name customer
            cell = row.createCell(2,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getName());

            // col address cus
            cell = row.createCell(3,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getAddress());

            // col phone cus
            cell = row.createCell(4,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getNumberPhone());

            // col email cus
            cell = row.createCell(5,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getEmail());

            // col in total inv
            cell = row.createCell(6,CellType.NUMERIC);
            cell.setCellValue(invoiceCustomerReport.getInTotal());

            // col out total inv
            cell = row.createCell(7,CellType.NUMERIC);
            cell.setCellValue(invoiceCustomerReport.getOutTotal());

            // col create date inv
            cell = row.createCell(8,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getCreatedDate());

            // col update date inv
            cell = row.createCell(9,CellType.STRING);
            cell.setCellValue(invoiceCustomerReport.getUpdatedDate());


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
        File file = new File("E:\\Do An\\T3H\\InvoiceExport.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
