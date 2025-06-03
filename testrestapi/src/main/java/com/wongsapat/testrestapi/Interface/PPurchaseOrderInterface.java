package com.wongsapat.testrestapi.Interface;

import java.util.List;

import com.wongsapat.testrestapi.Entity.PurchaseOrderEntity;

public interface PPurchaseOrderInterface {
    PurchaseOrderEntity insertPO(PurchaseOrderEntity purchaseOrder);
    List<PurchaseOrderEntity> findAllPO();
    PurchaseOrderEntity findPOById(Integer id);
    void deletePO(Integer id);
}
