package com.java1906.climan.data.repo;

import com.java1906.climan.data.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Integer> {

}
