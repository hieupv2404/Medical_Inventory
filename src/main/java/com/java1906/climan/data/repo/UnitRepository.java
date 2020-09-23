package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.Category;
import com.java1906.climan.data.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

}
