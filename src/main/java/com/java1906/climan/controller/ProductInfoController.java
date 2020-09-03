package com.java1906.climan.controller;

import com.java1906.climan.data.model.CategoryValue;
import com.java1906.climan.data.model.ProductInfo;
import com.java1906.climan.data.repo.CategoryValueRepository;
import com.java1906.climan.interceptor.HasRole;
import com.java1906.climan.services.ICategoryValueService;
import com.java1906.climan.services.IProducInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    //Get all product
    @GetMapping("/productInfo")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<Iterable<ProductInfo>> showProductList() {
        Iterable<ProductInfo> productList = productInfoService.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // Create product
    @PostMapping("/productInfo")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<ProductInfo> createProduct(@RequestBody ProductInfo productInfo) {
        // Sửa lý liên kêt n-n
        List<CategoryValue> newCategoryValue = new ArrayList<>();
        if(productInfo.getCategoryValues() !=null){
            for(CategoryValue categoryValue : productInfo.getCategoryValues()){
                categoryValueService.save(categoryValue);
                newCategoryValue.add(categoryValue);
            }
        }
        productInfo.setCategoryValues(newCategoryValue);
        ProductInfo productInfo1 = new ProductInfo(productInfo.getName(),productInfo.getDescription(),
                productInfo.getImg_url(),productInfo.getActiveFlag(),productInfo.getCreateDate(),
                productInfo.getUpdateDate(),productInfo.getCategoryValues());

        return new ResponseEntity<ProductInfo>(productInfoService.save(productInfo1),HttpStatus.CREATED);
    }

    // Update product
    @PutMapping("/productInfo/{productInfoId}")
    @HasRole({"STAFF", "ADMIN", "DOCTOR"})
    public ResponseEntity<ProductInfo> updateProduct(@PathVariable("productInfoId") int productInfoId,
                                                     @RequestBody ProductInfo productInfo) {
        List<CategoryValue> newCategoryValue = new ArrayList<>();
        if(productInfo.getCategoryValues() != null){
            for(CategoryValue categoryValue : productInfo.getCategoryValues()){
                categoryValueService.save(categoryValue);
                newCategoryValue.add(categoryValue);
            }
        }
        productInfo.setCategoryValues(newCategoryValue);
        Optional<ProductInfo> productInfoMaster =productInfoService.findById(productInfoId);
        if(!productInfoMaster.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    @DeleteMapping("/productInfo/{productInfoId}")
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
