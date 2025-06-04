package com.wongsapat.testrestapi.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wongsapat.testrestapi.Entity.PurchaseOrderEntity;
import com.wongsapat.testrestapi.Exception.ResourceNotFoundException;
import com.wongsapat.testrestapi.Models.PurchaseOrderHeaderDTO;
import com.wongsapat.testrestapi.Payload.ApiResponse;
import com.wongsapat.testrestapi.Services.PurchaseOrderService;
import com.wongsapat.testrestapi.Utils.PurchaseOrderMapper;


@RestController
@RequestMapping("/api")
public class PurchaseOrderController {

    private final PurchaseOrderService poService;

    public PurchaseOrderController(PurchaseOrderService poService) {
        this.poService = poService;
    }

    @GetMapping("/PO")
    public List<PurchaseOrderHeaderDTO> getAllPurchaseOrder() {
        List<PurchaseOrderEntity> entities = poService.getAllPurchaseOrders();
        return entities.stream()
                    .map(PurchaseOrderMapper::mapHeaderEntityToDTO)
                    .collect(Collectors.toList());
    }

    @GetMapping("/PO/{docEntry}")
    public PurchaseOrderHeaderDTO getPurchaseOrder(@PathVariable int docEntry) {
        PurchaseOrderEntity purchaseOrder = poService.getPurchaseOrderById(docEntry);
        if (purchaseOrder != null) {
            return PurchaseOrderMapper.mapHeaderEntityToDTO(purchaseOrder);
        } else {
            throw new ResourceNotFoundException("PurchaseOrder not found with id: " + docEntry);
        }
    }

    @PostMapping("/PO")
        public ResponseEntity<ApiResponse<PurchaseOrderHeaderDTO>> insertPO(@RequestBody PurchaseOrderHeaderDTO dto) {
            PurchaseOrderEntity savedEntity = poService.createPurchaseOrder(dto);
            PurchaseOrderHeaderDTO responseDTO = PurchaseOrderMapper.mapHeaderEntityToDTO(savedEntity);
            return ResponseEntity.ok(new ApiResponse<>("success", responseDTO));
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
