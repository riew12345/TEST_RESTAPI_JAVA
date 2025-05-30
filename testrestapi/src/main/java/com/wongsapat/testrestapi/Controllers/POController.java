package com.wongsapat.testrestapi.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.wongsapat.testrestapi.Models.PurchaseOrder;
import com.wongsapat.testrestapi.Interface.POInterface;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class POController {

    private final POInterface pOInterface;

    public POController(POInterface pOInterface) {
        this.pOInterface = pOInterface;
    }

    @PostMapping("/PO")
    public PurchaseOrder insertPO(@RequestBody PurchaseOrder entity) {
        entity.setId(0);
        return pOInterface.insertPO(entity);
    }

    @GetMapping("/PO")
    public List<PurchaseOrder> getAllPO() {
        return pOInterface.findAllPO();
    }

    @GetMapping("/PO/{id}")
    public PurchaseOrder getEmployee(@PathVariable int id) {
        PurchaseOrder purchaseOrder = pOInterface.findPOById(id);
        if (purchaseOrder != null) {
            return purchaseOrder;
        } else {
            throw new RuntimeException("PurchaseOrder not found with id: " + id);
        }
    }

    @PutMapping("/PO/{id}")
    public PurchaseOrder updateEmployee(@RequestBody PurchaseOrder entity) {
        return pOInterface.insertPO(entity);
    }
    
    @DeleteMapping("/PO/{id}")
    public void deleteEmployee(@PathVariable int id) {
        PurchaseOrder purchaseOrder = pOInterface.findPOById(id);
        if (purchaseOrder != null) {
            pOInterface.deletePO(id);
        } else {
            throw new RuntimeException("PurchaseOrder not found with id: " + id);
        }        
    }
}
