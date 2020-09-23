package com.java1906.climan.services.impl;

import com.java1906.climan.controller.ResourceNotFoundException;
import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.Unit;
import com.java1906.climan.data.repo.UnitRepository;
import com.java1906.climan.services.ICategoryService;
import com.java1906.climan.services.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UnitServiceImpl implements IUnitService {

    @Autowired
    private UnitRepository unitRepository;




    @Override
    public Optional<Unit> findById(Integer id) {
        if (!unitRepository.existsById(id)) {
            try {
                throw new ResourceNotFoundException("Unit with"+ id+ "not found");
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return unitRepository.findById(id);
    }


}
