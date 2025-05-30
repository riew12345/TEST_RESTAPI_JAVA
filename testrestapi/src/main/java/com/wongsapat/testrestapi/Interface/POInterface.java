package com.wongsapat.testrestapi.Interface;

import java.util.List;

import com.wongsapat.testrestapi.Models.PurchaseOrder;

public interface POInterface {
    PurchaseOrder insertPO(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> findAllPO();
    PurchaseOrder findPOById(Integer id);
    void deletePO(Integer id);
}
