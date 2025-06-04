package com.wongsapat.testrestapi.Utils;

import java.util.List;
import java.util.stream.Collectors;

import com.wongsapat.testrestapi.Entity.PurchaseOrderEntity;
import com.wongsapat.testrestapi.Models.PurchaseOrderDetailDTO;
import com.wongsapat.testrestapi.Models.PurchaseOrderHeaderDTO;

public class PurchaseOrderMapper {

    public static PurchaseOrderHeaderDTO mapHeaderEntityToDTO(PurchaseOrderEntity po) {
        PurchaseOrderHeaderDTO dto = new PurchaseOrderHeaderDTO();
        dto.setDocEntry(String.valueOf(po.getDocEntry()));
        dto.setDocNum(po.getDocNum());
        dto.setDocDate(po.getDocDate());
        dto.setCardCode(po.getCardCode());
        dto.setCardName(po.getCardName());
        dto.setDocStatus(po.getDocStatus());
        dto.setDocTotal(po.getDocTotal());
        dto.setComments(po.getComments());

        // เรียกใช้ฟังก์ชัน map รายการ detail
        List<PurchaseOrderDetailDTO> details = po.getPor1Lines().stream()
            .map(PurchaseOrderMapper::mapDetailEntityToDTO)
            .collect(Collectors.toList());

        dto.setPor1Lines(details);

        return dto;
    }

    public static PurchaseOrderDetailDTO mapDetailEntityToDTO(PurchaseOrderEntity.PurchaseOrderDetail detail) {
        PurchaseOrderDetailDTO dto = new PurchaseOrderDetailDTO();

        if (detail.getDocEntry() != null && detail.getDocEntry().getDocEntry() != null) {
            dto.setDocEntry(String.valueOf(detail.getDocEntry().getDocEntry()));
        } else {
            dto.setDocEntry(null);
        }

        if (detail.getDocEntry() != null) {
            dto.setLineNum(detail.getDocEntry().getLineNum());
        } else {
            dto.setLineNum(null);
        }

        dto.setItemCode(detail.getItemCode());
        dto.setDscription(detail.getDscription());
        dto.setQuantity(detail.getQuantity());
        dto.setPrice(detail.getPrice());
        dto.setLineTotal(detail.getLineTotal());

        return dto;
    }
}
