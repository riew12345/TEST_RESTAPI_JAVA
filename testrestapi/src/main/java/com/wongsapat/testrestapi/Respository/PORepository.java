package com.wongsapat.testrestapi.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wongsapat.testrestapi.Models.PurchaseOrder;

public interface PORepository extends JpaRepository<PurchaseOrder, Integer> {
    

}
