package com.wongsapat.testrestapi.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wongsapat.testrestapi.Entity.PurchaseOrderEntity;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Integer> {
    

}
