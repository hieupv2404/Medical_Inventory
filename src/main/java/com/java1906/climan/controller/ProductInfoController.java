package com.java1906.climan.controller;

import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.ProductInfo;
import com.java1906.climan.data.model.ProductInfoCategoryValue;
import com.java1906.climan.data.repo.CategoryValueRepository;
import com.java1906.climan.data.repo.ProductInStockRepository;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryValueService;
import com.java1906.climan.services.IProducInfoService;
import com.java1906.climan.services.IProductInfoCategoryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
class ProductInfoController {

    @Autowired
    private IProducInfoService productInfoService;
    @Autowired
    private ICategoryValueService categoryValueService;

//    @Autowired
//    private CategoryValueByProduct categoryValueByProduct;

    @Autowired
    private IProductInfoCategoryValueService productInfoCategoryValueService;

    @Autowired
    private ProductInStockRepository productInStockRepository;
    //Get all product
    @GetMapping("/products-info")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<List<ProductInfo>> showProductList(@RequestParam("name") String productName) {
        List<ProductInfo> productList = productInfoService.findAll(productName);
        for (ProductInfo productInfo: productList)
        {
            productInfo.setCategoryValueByFate(categoryValueService.findCategoryValueByFate(productInfo.getId()).getName());
            productInfo.setCategoryValueByProperty(categoryValueService.findCategoryValueByProperty(productInfo.getId()).getName());
            productInfo.setQtyInStock(productInStockRepository.findByProduct(productInfo.getId()).getQty());
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //Get all product by id
    @GetMapping("/products-info/{productId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<ProductInfo> showProductById(@PathVariable("productId") Integer productId) {
        ProductInfo productInfo = productInfoService.findById(productId).get();
        if (productInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productInfo,HttpStatus.OK);
    }

    // Create product
    @PostMapping("/products-info")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<ProductInfo> createProduct(@RequestBody ProductInfo productInfo) {
        // Xu lý liên kêt n-n
//        List<CategoryValue> newCategoryValue = new ArrayList<>();
        if(productInfo.getCategoryValues() !=null){
            for(CategoryValue categoryValue : productInfo.getCategoryValues()){
                ProductInfoCategoryValue productInfoCategoryValue = new ProductInfoCategoryValue();
                productInfoCategoryValue.setProductInfoId(productInfo.getId());
                productInfoCategoryValue.setCategoryValueId(categoryValue.getId());
                productInfoCategoryValueService.save(productInfoCategoryValue);
            }
        }
        return new ResponseEntity<ProductInfo>(productInfoService.save(productInfo),HttpStatus.CREATED);
    }

    // Update product
    @PutMapping("/products-info/{productInfoId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<ProductInfo> updateProduct(@PathVariable("productInfoId") int productInfoId,
                                                     @RequestBody ProductInfo productInfo) {
        Optional<ProductInfo> productInfoMaster =productInfoService.findById(productInfoId);
        if(!productInfoMaster.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CategoryValue> categoryValueListProductInfoMaster = productInfoMaster.get().getCategoryValues();
        if(productInfo.getCategoryValues() != null) {
            for (CategoryValue categoryValue : productInfo.getCategoryValues()) {
                for (CategoryValue categoryValueMaster : categoryValueListProductInfoMaster) {
                    if (categoryValue.getCategory().getId() == categoryValueMaster.getCategory().getId()
                            && categoryValue.getId() != categoryValueMaster.getId()) {
                        ProductInfoCategoryValue productInfoCategoryValue = productInfoCategoryValueService.findByProductIdAndCategoryValueId(productInfoId, categoryValueMaster.getId());
                        productInfoCategoryValue.setCategoryValueId(categoryValue.getId());
                        break;
                    }
                }
            }
        }
        productInfoMaster.get().setName(productInfo.getName());
        productInfoMaster.get().setDescription(productInfo.getDescription());
        productInfoMaster.get().setActiveFlag(productInfo.getActiveFlag());
        productInfoMaster.get().setImg_url(productInfo.getImg_url());
        productInfoMaster.get().setCategoryValues(productInfo.getCategoryValues());
        productInfoMaster.get().setUpdateDate(productInfo.getUpdateDate());
        return new ResponseEntity<>(productInfoService.save(productInfoMaster.get()), HttpStatus.CREATED);
    }

    // Delete product
    @DeleteMapping("/products-info/{productInfoId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<String> deleteProduct(@PathVariable("productInfoId") Integer productInfoId) {
        productInfoService.delete(productInfoId);
        return new ResponseEntity<String>("delete OK",HttpStatus.OK);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("./uploadfolder/image/" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
    }

}
