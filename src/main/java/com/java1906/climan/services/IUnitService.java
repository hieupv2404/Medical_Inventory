package com.java1906.climan.services;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.Unit;

import java.util.List;
import java.util.Optional;

public interface IUnitService {

    Optional<Unit> findById(Integer id);



}
