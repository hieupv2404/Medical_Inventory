package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Invoice;
import com.java1906.climan.data.model.InvoiceItem;
import com.java1906.climan.data.model.ProductInStock;
import com.java1906.climan.data.model.ProductInfo;
import com.java1906.climan.data.repo.InvoiceItemRepository;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.ProductInStockRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.services.IInvoiceItemService;
import com.java1906.climan.services.IUnitService;
import com.java1906.climan.services.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvoiceItemServiceImpl implements IInvoiceItemService {
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductInStockRepository productInStockRepository;

    @Autowired
    private ProductInStockService productInStockService;

    @Autowired
    private IUnitService unitService;




    @Override
    public List<InvoiceItem> findAll() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public Optional<InvoiceItem> findById(Integer invoiceItemId) {
        if(!invoiceItemRepository.existsById(invoiceItemId)){
            try{
                throw new ResourceNotFoundException("Invoice item with"+invoiceItemId+"not found");
            }catch (ResourceNotFoundException e){
                e.printStackTrace();
            }
        }
        return invoiceItemRepository.findById(invoiceItemId);
    }

    @Override
    public InvoiceItem save(int invoiceId, InvoiceItem invoiceItem, Integer unitId, int productId) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();


        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
//        Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(productInfoId);
        if(!invoiceOptional.isPresent()){
            try {
                throw new ResourceNotFoundException("Invoice with id "+invoiceId+ "does not exist");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(productId);
        ProductInfo productInfo = productInfoOptional.get();
        Invoice invoice1 = invoiceOptional.get();
        invoiceItem.setInvoice(invoice1);
        invoiceItem.setProductInfo(productInfo);
        invoiceItem.setPriceInTotal(invoiceItem.getQty()*invoiceItem.getPriceIn());
        invoiceItem.setPriceOutTotal(invoiceItem.getQty()*invoiceItem.getPriceOut());
        invoiceItem.setUnit(unitService.findById(unitId).get());
        InvoiceItem invoiceItem1 = invoiceItemRepository.save(invoiceItem);
        invoice1.getInvoiceItems().add(invoiceItem1);
        invoice1.setInTotal(invoice1.getInTotal()+ invoiceItem1.getPriceInTotal());
        invoice1.setOutTotal(invoice1.getOutTotal()+ invoiceItem1.getPriceOutTotal());
//        productInStockRepository.save(new ProductInStock(productInfo, invoiceItem.getQty(), invoiceItem.getUnit(), invoiceItem.getPriceInTotal()));
        productInStockService.saveOrUpdate(invoiceItem);
        return invoiceItem1;
    }

    @Override
    public InvoiceItem update(Integer invoiceItemId, Integer productId, Integer unitId, InvoiceItem invoiceItem) {
        if(!invoiceItemRepository.existsById(invoiceItemId)){
            try {
                throw new ResourceNotFoundException("Invoice item with id "+invoiceItemId + "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Optional<InvoiceItem> invoiceItem1 =invoiceItemRepository.findById(invoiceItemId);
        if(!invoiceItem1.isPresent()){
            try {
                throw new ResourceNotFoundException("Invoice item with"+invoiceItemId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }

        InvoiceItem invoiceItem2 =invoiceItem1.get();
        invoiceItem2.setProductInfo(productInfoRepository.findById(productId).get());
        invoiceItem2.setPriceIn(invoiceItem.getPriceIn());
        invoiceItem2.setPriceOut(invoiceItem.getPriceOut());
        invoiceItem2.setQty(invoiceItem.getQty());
        invoiceItem2.setUnit(unitService.findById(unitId).get());
        invoiceItem2.setPriceInTotal(invoiceItem.getQty()*invoiceItem.getPriceIn());
        invoiceItem2.setPriceOutTotal(invoiceItem.getQty()*invoiceItem.getPriceOut());

        Invoice invoice1 = invoiceRepository.findById(invoiceItem2.getInvoice().getId()).get();
        invoice1.setInTotal(invoice1.getInTotal()+ invoiceItem2.getPriceInTotal());
        invoice1.setOutTotal(invoice1.getOutTotal()+ invoiceItem2.getPriceOutTotal());
        invoiceRepository.save(invoice1);
        productInStockService.saveOrUpdate(invoiceItem2);

        return invoiceItemRepository.save(invoiceItem2);
    }

    @Override
    public void delete(Integer invoiceItemId) {
        if (!invoiceItemRepository.existsById(invoiceItemId)) {
            try {
                throw new ResourceNotFoundException("Invoice Item with id " + invoiceItemId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        invoiceItemRepository.deleteById(invoiceItemId);
    }

    @Override
    public List<InvoiceItem> findAllByInvoiceId(int invoiceId) {
        List <InvoiceItem> invoiceItemList = invoiceItemRepository.findAll();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        Invoice invoice = invoiceOptional.get();
        List<InvoiceItem> invoiceItemByInvoiceId = new ArrayList<>();
        for (InvoiceItem invoiceItem : invoiceItemList)
        {
            if (invoiceItem.getInvoice() == invoice )
            {
                invoiceItemByInvoiceId.add(invoiceItem);
            }
        }
        return invoiceItemByInvoiceId;
    }
}
