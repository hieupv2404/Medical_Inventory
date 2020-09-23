package com.java1906.climan.services.impl;

import com.java1906.climan.data.model.*;
import com.java1906.climan.data.repo.InvoiceItemRepository;
import com.java1906.climan.data.repo.InvoiceRepository;
import com.java1906.climan.data.repo.ProductInStockRepository;
import com.java1906.climan.data.repo.ProductInfoRepository;
import com.java1906.climan.services.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductInStockServiceImpl implements ProductInStockService {
    @Autowired
    private ProductInStockRepository productInStockRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInStock> findAll() {
        return productInStockRepository.findAll();
    }

    @Override
    public void saveOrUpdate(InvoiceItem invoiceItem) {
        if (invoiceItem.getProductInfo() != null) {
            int productId = invoiceItem.getProductInfo().getId();
            List<ProductInStock> productInStocks = productInStockRepository.findAll();
            int checkExists = 0;
            for (ProductInStock productInStock : productInStocks) {
                if (productInStock.getProductInfo().getId() == productId) {
                    checkExists = 1;
                    if (invoiceItem.getInvoice().getType() == 1) {
                        productInStock.setQty(productInStock.getQty() + invoiceItem.getQty());
                        productInStock.setPrice(invoiceItem.getPriceIn());
                    } else if (invoiceItem.getInvoice().getType() == 2) {
                        productInStock.setQty(productInStock.getQty() - invoiceItem.getQty());
                    }
                    productInStock.setUpdatedDate(new Date());
                    productInStockRepository.save(productInStock);
                }
            }
            if (checkExists == 0) {
                if (invoiceItem.getInvoice().getType() == 1) {
                    ProductInStock productInStock = new ProductInStock();
                    productInStock.setProductInfo(invoiceItem.getProductInfo());
                    productInStock.setQty(invoiceItem.getQty());
                    productInStock.setUnit(invoiceItem.getUnit());
                    productInStock.setPrice(invoiceItem.getPriceIn());
                    productInStock.setActiveFlag(1);
                    productInStock.setCreatedDate(new Date());
                    productInStock.setUpdatedDate(new Date());
                    productInStockRepository.save(productInStock);
                }
            }
        }
    }

//    @Override
//    public double convertUnit(double qtyInp, UnitConstant unitInp, double qtySaved, UnitConstant unitSaved) {
//        double qtyOut = 0;
//        if (unitSaved.equals(UnitConstant.GAM)) {
//            switch (unitInp) {
//                case CAN:
//                    qtyOut= (qtyInp * 500)+qtySaved;
//                    break;
//                case LY:
//                    qtyOut= (qtyInp * 0.03)+qtySaved;
//                    break;
//                case DONGCAN:
//                    qtyOut= (qtyInp * 3.1)+qtySaved;
//                    break;
//                case LANG:
//                    qtyOut= (qtyInp * 31.25)+qtySaved;
//                    break;
//                case PHAN:
//                    qtyOut= (qtyInp * 0.31)+qtySaved;
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + unitInp);
//            }
//            return qtyOut;
//        } else {
//            switch (unitInp) {
//                case CAN:
//                    if (unitSaved == UnitConstant.CAN) {
//                        qtyOut= (qtyInp + qtySaved);
//                    }
//
//                    if (unitSaved == UnitConstant.DONGCAN) {
//                        qtySaved *= 3.1;
//                        qtyInp *= 500;
//                        qtyOut= (qtyInp + qtySaved) / 3.1;
//                    }
//
//                    if (unitSaved == UnitConstant.LANG) {
//                        qtySaved *= 31.25;
//                        qtyInp *= 500;
//                        qtyOut= (qtyInp + qtySaved) / 31.25;
//                    }
//
//                    if (unitSaved == UnitConstant.PHAN) {
//                        qtySaved *= 0.31;
//                        qtyInp *= 500;
//                        qtyOut= (qtyInp + qtySaved) / 0.31;
//                    }
//
//                    if (unitSaved == UnitConstant.LY) {
//                        qtySaved *= 0.03;
//                        qtyInp *= 500;
//                        qtyOut= (qtyInp + qtySaved) / 0.03;
//                    }
//
//                case LY:
//                    if (unitSaved == UnitConstant.CAN) {
//                        qtySaved *= 500;
//                        qtyInp *= 0.03;
//                        qtyOut= (qtyInp + qtySaved) / 500;
//                    }
//
//                    if (unitSaved == UnitConstant.DONGCAN) {
//                        qtySaved *= 3.1;
//                        qtyInp *= 0.03;
//                        qtyOut= (qtyInp + qtySaved) / 3.1;
//                    }
//
//                    if (unitSaved == UnitConstant.LANG) {
//                        qtySaved *= 31.25;
//                        qtyInp *= 0.03;
//                        qtyOut= (qtyInp + qtySaved) / 31.25;
//                    }
//
//                    if (unitSaved == UnitConstant.PHAN) {
//                        qtySaved *= 0.31;
//                        qtyInp *= 0.03;
//                        qtyOut= (qtyInp + qtySaved) / 0.31;
//                    }
//
//                    if (unitSaved == UnitConstant.LY) {
//                        qtyOut= (qtyInp + qtySaved);
//                    }
//                    break;
//                case DONGCAN:
//                    if (unitSaved == UnitConstant.CAN) {
//                        qtySaved *= 500;
//                        qtyInp *= 3.1;
//                        qtyOut= (qtyInp + qtySaved) / 500;
//                    }
//
//                    if (unitSaved == UnitConstant.DONGCAN) {
//                        qtyOut= (qtyInp + qtySaved);
//                    }
//
//                    if (unitSaved == UnitConstant.LANG) {
//                        qtySaved *= 31.25;
//                        qtyInp *= 3.1;
//                        qtyOut= (qtyInp + qtySaved) / 31.25;
//                    }
//
//                    if (unitSaved == UnitConstant.PHAN) {
//                        qtySaved *= 0.31;
//                        qtyInp *= 3.1;
//                        qtyOut= (qtyInp + qtySaved) / 0.31;
//                    }
//
//                    if (unitSaved == UnitConstant.LY) {
//                        qtySaved *= 0.03;
//                        qtyInp *= 3.1;
//                        qtyOut= (qtyInp + qtySaved) / 0.03;
//                    }
//                    break;
//                case LANG:
//                    if (unitSaved == UnitConstant.CAN) {
//                        qtySaved *= 500;
//                        qtyInp *= 31.25;
//                        qtyOut= (qtyInp + qtySaved) / 500;
//                    }
//
//                    if (unitSaved == UnitConstant.DONGCAN) {
//                        qtySaved *= 3.1;
//                        qtyInp *= 31.25;
//                        qtyOut= (qtyInp + qtySaved) / 3.1;
//                    }
//
//                    if (unitSaved == UnitConstant.LANG) {
//                        qtyOut= (qtyInp + qtySaved);
//                    }
//
//                    if (unitSaved == UnitConstant.PHAN) {
//                        qtySaved *= 0.31;
//                        qtyInp *= 31.25;
//                        qtyOut= (qtyInp + qtySaved) / 0.31;
//                    }
//
//                    if (unitSaved == UnitConstant.LY) {
//                        qtySaved *= 0.03;
//                        qtyInp *= 31.25;
//                        qtyOut= (qtyInp + qtySaved) / 0.03;
//                    }
//                    break;
//                case PHAN:
//                    if (unitSaved == UnitConstant.CAN) {
//                        qtySaved *= 500;
//                        qtyInp *= 0.31;
//                        qtyOut= (qtyInp + qtySaved) / 500;
//                    }
//
//                    if (unitSaved == UnitConstant.DONGCAN) {
//                        qtySaved *= 3.1;
//                        qtyInp *= 0.31;
//                        qtyOut= (qtyInp + qtySaved) / 3.1;
//                    }
//
//                    if (unitSaved == UnitConstant.LANG) {
//                        qtySaved *= 31.25;
//                        qtyInp *= 0.31;
//                        qtyOut= (qtyInp + qtySaved) / 31.25;
//                    }
//
//                    if (unitSaved == UnitConstant.PHAN) {
//                        qtyOut= (qtyInp + qtySaved);
//                    }
//
//                    if (unitSaved == UnitConstant.LY) {
//                        qtySaved *= 0.03;
//                        qtyInp *= 0.31;
//                        qtyOut= (qtyInp + qtySaved) / 0.03;
//                    }
//                    break;
//            }
//            return qtyOut;
//        }
//    }

    @Override
    public double changeValueByUnit(UnitConstant unitInp, double qtySaved) {
        double qtyOut=0;
        if (unitInp == UnitConstant.GAM)
            return qtySaved;
        else
        {
            switch (unitInp) {
                case CAN:
                    qtyOut=  qtySaved / 500;
                    break;
                case LY:
                    qtyOut = qtySaved / 0.03;
                    break;
                case DONGCAN:
                   qtyOut = qtySaved / 3.1;
                    break;
                case LANG:
                    qtyOut = qtySaved / 31.25;
                    break;
                case PHAN:
                    qtyOut = qtySaved / 0.31;
                    break;
            }
            return qtyOut;
        }
    }

    @Override
    public double importConvertToGam(double qtyInp, UnitConstant unitInp) {
        double qtyOut = 0;
        if (unitInp == UnitConstant.GAM) {
            return qtyInp;
        } else {
            switch (unitInp) {
                case CAN:
                    qtyOut = (qtyInp * 500);
                    break;
                case LY:
                    qtyOut = (qtyInp * 0.03);
                    break;
                case DONGCAN:
                    qtyOut = (qtyInp * 3.1);
                    break;
                case LANG:
                    qtyOut = (qtyInp * 31.25);
                    break;
                case PHAN:
                    qtyOut = (qtyInp * 0.31);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + unitInp);
            }
            return qtyOut;
        }
    }

}

