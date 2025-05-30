package com.wongsapat.testrestapi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wongsapat.testrestapi.Interface.POInterface;
import com.wongsapat.testrestapi.Models.PurchaseOrder;
import com.wongsapat.testrestapi.Respository.PORepository;

@Service
public class POService implements POInterface {

    // Assuming you have a PurchaseOrderRepository similar to EmployeeRepository
    @Autowired
    private PORepository poRepository;

    public POService(PORepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public PurchaseOrder insertPO(PurchaseOrder purchaseOrder) {
        return poRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> findAllPO() {
        return poRepository.findAll();
    }
    
    @Override
    public PurchaseOrder findPOById(Integer id) {
        Optional<PurchaseOrder> purchaseOrder = poRepository.findById(id);

        if (purchaseOrder.isPresent()) {
            return purchaseOrder.get();
        } else {
            throw new RuntimeException("Purchase Order not found with id: " + id);
        }
    }

    @Override
    public void deletePO(Integer id) {
        if (poRepository.existsById(id)) {
            poRepository.deleteById(id);
        } else {
            throw new RuntimeException("Purchase Order not found with id: " + id);
        }
    }
}
