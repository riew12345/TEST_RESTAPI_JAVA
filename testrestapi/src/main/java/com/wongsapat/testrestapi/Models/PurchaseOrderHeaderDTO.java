package com.wongsapat.testrestapi.Models;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderHeaderDTO  {
    public String docNum;
    public LocalDateTime docDate;
    public String cardCode;
    public String cardName;
    public String docStatus;
    public String comments;
    public List<PurchaseOrderDetailDTO> por1Lines;

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public LocalDateTime getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDateTime docDate) {
        this.docDate = docDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<PurchaseOrderDetailDTO> getPor1Lines() {
        return por1Lines;
    }

    public void setPor1Lines(List<PurchaseOrderDetailDTO> por1Lines) {
        this.por1Lines = por1Lines;
    }
}