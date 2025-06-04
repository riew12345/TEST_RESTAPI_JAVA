package com.wongsapat.testrestapi.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wongsapat.testrestapi.Entity.PurchaseOrderEntity;
import com.wongsapat.testrestapi.Models.PurchaseOrderDetailDTO;
import com.wongsapat.testrestapi.Models.PurchaseOrderHeaderDTO;
import com.wongsapat.testrestapi.Respository.PurchaseOrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository poRepo;

    public PurchaseOrderService(PurchaseOrderRepository poRepo) {
        this.poRepo = poRepo;
    }

    public PurchaseOrderHeaderDTO convertToResponseDTO(PurchaseOrderEntity po) {
        PurchaseOrderHeaderDTO dto = new PurchaseOrderHeaderDTO();
        dto.setDocEntry(String.valueOf(po.getDocEntry())); // แปลง Integer → String
        dto.setDocNum(po.getDocNum());
        dto.setDocDate(po.getDocDate());
        dto.setCardCode(po.getCardCode());
        dto.setCardName(po.getCardName());
        dto.setDocStatus(po.getDocStatus());
        dto.setComments(po.getComments());

        List<PurchaseOrderDetailDTO> detailDTOs = po.getPor1Lines().stream().map(detail -> {
            PurchaseOrderDetailDTO detailDTO = new PurchaseOrderDetailDTO();
            detailDTO.setDocEntry(String.valueOf(po.getDocEntry())); // ใช้ docEntry จาก header
            detailDTO.setLineNum(detail.getLineNum());
            detailDTO.setItemCode(detail.getItemCode());
            detailDTO.setDscription(detail.getDscription());
            detailDTO.setQuantity(detail.getQuantity());
            detailDTO.setPrice(detail.getPrice());
            return detailDTO;
        }).collect(Collectors.toList());

        dto.setPor1Lines(detailDTOs);

        return dto;
    }


    public List<PurchaseOrderEntity> getAllPurchaseOrders() {
        return poRepo.findAll();
    }

    public PurchaseOrderEntity getPurchaseOrderById(Integer docEntry) {
        return poRepo.findById(docEntry)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));
    }

    @Transactional
    public PurchaseOrderEntity createPurchaseOrder(PurchaseOrderHeaderDTO dto) {
        PurchaseOrderEntity po = new PurchaseOrderEntity();
        po.setDocNum(dto.getDocNum());
        po.setDocDate(dto.getDocDate());
        po.setCardCode(dto.getCardCode());
        po.setCardName(dto.getCardName());
        po.setDocStatus(dto.getDocStatus());
        po.setComments(dto.getComments());
        po.setCreatedDate(LocalDateTime.now());

        dto.getPor1Lines().forEach(lineDTO -> {
            PurchaseOrderEntity.PurchaseOrderDetail detail = new PurchaseOrderEntity.PurchaseOrderDetail();

            PurchaseOrderEntity.PurchaseOrderDetail.POR1Key key = new PurchaseOrderEntity.PurchaseOrderDetail.POR1Key();
            key.setDocEntry(null);
            key.setLineNum(lineDTO.getLineNum());

            detail.setDocEntry(key);
            detail.setItemCode(lineDTO.getItemCode());
            detail.setDscription(lineDTO.getDscription());
            detail.setQuantity(lineDTO.getQuantity());
            detail.setPrice(lineDTO.getPrice());
            detail.setLineTotal(detail.getPrice() * detail.getQuantity());

            po.addDetail(detail);
        });

        double total = po.getPor1Lines().stream()
            .mapToDouble(PurchaseOrderEntity.PurchaseOrderDetail::getLineTotal)
            .sum();
        po.setDocTotal(total);

        return poRepo.save(po);
    }

    @Transactional
    public PurchaseOrderEntity updatePurchaseOrder(Integer docEntry, PurchaseOrderHeaderDTO dto) {
        PurchaseOrderEntity po = poRepo.findById(docEntry)
            .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        po.setDocNum(dto.docNum);
        po.setDocDate(dto.docDate);
        po.setCardCode(dto.cardCode);
        po.setCardName(dto.cardName);
        po.setDocStatus(dto.docStatus);
        po.setComments(dto.comments);
        po.setUpdatedDate(LocalDateTime.now());

        po.getPor1Lines().clear();

        dto.por1Lines.forEach(lineDTO -> {
            PurchaseOrderEntity.PurchaseOrderDetail detail = new PurchaseOrderEntity.PurchaseOrderDetail();
            PurchaseOrderEntity.PurchaseOrderDetail.POR1Key key = new PurchaseOrderEntity.PurchaseOrderDetail.POR1Key();
            key.setDocEntry(docEntry);
            key.setLineNum(lineDTO.lineNum);
            detail.setDocEntry(key);
            detail.setItemCode(lineDTO.itemCode);
            detail.setDscription(lineDTO.dscription);
            detail.setQuantity(lineDTO.quantity);
            detail.setPrice(lineDTO.price);
            detail.setOpor(po);
            po.getPor1Lines().add(detail);
        });

        return poRepo.save(po);
    }

    public void deletePurchaseOrder(Integer docEntry) {
        poRepo.deleteById(docEntry);
    }
}
