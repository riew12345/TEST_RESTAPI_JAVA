package com.wongsapat.testrestapi.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wongsapat.testrestapi.Entity.PurchaseOrderEntity;
import com.wongsapat.testrestapi.Models.PurchaseOrderHeaderDTO;
import com.wongsapat.testrestapi.Services.PurchaseOrderService;


@RestController
@RequestMapping("/api")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    public PurchaseOrderController(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @GetMapping("/PO")
    public List<PurchaseOrderEntity> getAllPO() {
        return poService.getAllPurchaseOrders();
    }

    @GetMapping("/PO/{docEntry}")
    public PurchaseOrderEntity getEmployee(@PathVariable int docEntry) {
        PurchaseOrderEntity purchaseOrder = poService.getPurchaseOrderById(docEntry);
        if (purchaseOrder != null) {
            return purchaseOrder;
        } else {
            throw new RuntimeException("PurchaseOrder not found with id: " + docEntry);
        }
    }

    @PostMapping("/PO")
    public ResponseEntity<PurchaseOrderEntity> insertPO(@RequestBody PurchaseOrderHeaderDTO entity) {
        PurchaseOrderEntity createOrder = poService.createPurchaseOrder(entity);
        return new ResponseEntity<>(createOrder, HttpStatus.CREATED);
    }

    @PutMapping("/PO/{docEntry}")
    public ResponseEntity<PurchaseOrderEntity> updateEmployee(@PathVariable Integer docEntry,@RequestBody PurchaseOrderHeaderDTO entity) {
        PurchaseOrderEntity updatOrder = poService.updatePurchaseOrder(docEntry, entity);
        return ResponseEntity.ok(updatOrder);
    }
    
    @DeleteMapping("/PO/{docEntry}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer docEntry) {
        PurchaseOrderEntity purchaseOrder = poService.getPurchaseOrderById(docEntry);
        if (purchaseOrder != null) {
            poService.deletePurchaseOrder(docEntry);
            return ResponseEntity.noContent().build();
        } else {
            throw new RuntimeException("PurchaseOrder not found with id: " + docEntry);
        }        
    }
}
