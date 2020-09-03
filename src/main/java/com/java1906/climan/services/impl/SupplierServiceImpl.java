package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.Supplier;
import com.java1906.climan.data.repo.SupplierRepository;
import com.java1906.climan.services.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(int supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            try {
                throw new ResourceNotFoundException("Category with"+ supplierId+ "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return supplierRepository.findById(supplierId);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier update(int supplierId, Supplier supplier) throws Exception {
        if (!supplierRepository.existsById(supplierId)) {
            try {
                throw new ResourceNotFoundException("Author with id " + supplierId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Optional<Supplier> supplierRepositoryById =supplierRepository.findById(supplierId);

        if (!supplierRepositoryById.isPresent()) {
            try {
                throw new ResourceNotFoundException("Author with id " + supplierId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        Supplier supplier1 = supplierRepositoryById.get();
        supplier1.setSupplierName(supplier.getSupplierName());
        supplier1.setSupplierAddress(supplier.getSupplierAddress());
        supplier1.setEmail(supplier.getNumberPhone());
        supplier1.setNumberPhone(supplier.getNumberPhone());

        return supplierRepository.save(supplier1);
    }

    @Override
    public void delete(Integer supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            try {
                throw new ResourceNotFoundException("Author with id " + supplierId + " not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public Supplier findByName(String name) {
        return supplierRepository.findSupplierNativeQueryByName(name);
    }
}

