package com.wongsapat.testrestapi.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Objects;

@Entity
@Table(name = "OPOR")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocEntry")
    private Integer docEntry;

    @Column(name = "DocNum", nullable = false, unique = true, length = 20)
    private String docNum;

    @Column(name = "DocDate", nullable = false)
    private LocalDateTime docDate;
    
    @Column(name = "CardCode", nullable = false, length = 20)
    private String cardCode;

    @Column(name = "CardName", nullable = false, length = 100)
    private String cardName;

    @Column(name = "DocStatus", nullable = false, length = 1)
    private String docStatus = "O";

    @Column(name = "DocTotal", nullable = false)
    private Double docTotal = 0.0;

    @Column(name = "Comments", length = 254)
    private String comments;

    @Column(name = "CreatedDate", insertable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "UpdatedDate")
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "opor", cascade = CascadeType.ALL, orphanRemoval = true)

    @JsonManagedReference
    private Set<PurchaseOrderDetail> por1Lines = new HashSet<>();

    public PurchaseOrderEntity() {
    }

    public PurchaseOrderEntity(Integer docEntry, String docNum, LocalDateTime docDate, String cardCode, String cardName,
                               String docStatus, Double docTotal, String comments, LocalDateTime createdDate,
                               LocalDateTime updatedDate, Set<PurchaseOrderDetail> por1Lines) {
        this.docEntry = docEntry;
        this.docNum = docNum;
        this.docDate = docDate;
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.docStatus = docStatus;
        this.docTotal = docTotal;
        this.comments = comments;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.por1Lines = por1Lines;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

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

    public Double getDocTotal() {
        return docTotal;
    }

    public void setDocTotal(Double docTotal) {
        this.docTotal = docTotal;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<PurchaseOrderDetail> getPor1Lines() {
        return por1Lines;
    }

    public void setPor1Lines(Set<PurchaseOrderDetail> por1Lines) {
        this.por1Lines = por1Lines;
    }

    public void addDetail(PurchaseOrderDetail detail) {
        por1Lines.add(detail);
        detail.setOpor(this);
    }

    public void removeDetail(PurchaseOrderDetail detail) {
        por1Lines.remove(detail);
        detail.setOpor(null);
    }

    @Entity
    @Table(name = "POR1")
    public static class PurchaseOrderDetail {

        @EmbeddedId
        private POR1Key docEntry;

        @Column(name = "ItemCode", nullable = false, length = 10)
        private String itemCode;

        @Column(name = "Dscription", length = 254)
        private String dscription;

        @Column(name = "Quantity", nullable = false)
        private Integer quantity = 1;

        @Column(name = "Price", nullable = false)
        private Double price = 0.0;

        @Column(name = "LineTotal", nullable = false)
        private Double lineTotal = 0.0;

        @ManyToOne
        @MapsId("docEntry")
        @JoinColumn(name = "DocEntry")
        @JsonBackReference
        private PurchaseOrderEntity opor;

        public PurchaseOrderDetail() {}

        public PurchaseOrderDetail(String itemCode, String dscription, Integer quantity, Double price, Double lineTotal, PurchaseOrderEntity opor) {
            this.itemCode = itemCode;
            this.dscription = dscription;
            this.quantity = quantity;
            this.price = price;
            this.lineTotal = lineTotal;
            this.opor = opor;
        }

        public POR1Key getDocEntry() {
            return docEntry;
        }

        public void setDocEntry(POR1Key docEntry) {
            this.docEntry = docEntry;
        }

        public Integer getLineNum() {
            return docEntry != null ? docEntry.getLineNum() : null;
        }

        public void setLineNum(Integer lineNum) {
            if (docEntry == null) docEntry = new POR1Key();
            docEntry.setLineNum(lineNum);
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public String getDscription() {
            return dscription;
        }

        public void setDscription(String dscription) {
            this.dscription = dscription;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getLineTotal() {
            return lineTotal;
        }

        public void setLineTotal(Double lineTotal) {
            this.lineTotal = lineTotal;
        }

        public PurchaseOrderEntity getOpor() {
            return opor;
        }

        public void setOpor(PurchaseOrderEntity opor) {
            this.opor = opor;
        }

        @Embeddable
        public static class POR1Key implements Serializable {
            @Column(name = "DocEntry")
            private Integer docEntry;

            @Column(name = "LineNum")
            private Integer lineNum;

            public POR1Key() {}

            public POR1Key(Integer docEntry, Integer lineNum) {
                this.docEntry = docEntry;
                this.lineNum = lineNum;
            }
            public Integer getDocEntry() {
                return docEntry;
            }
            public void setDocEntry(Integer docEntry) {
                this.docEntry = docEntry;
            }
            public Integer getLineNum() {
                return lineNum;
            }
            public void setLineNum(Integer lineNum) {
                this.lineNum = lineNum;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof POR1Key)) return false;
                POR1Key por1Key = (POR1Key) o;
                return Objects.equals(docEntry, por1Key.docEntry) && Objects.equals(lineNum, por1Key.lineNum);
            }

            @Override
            public int hashCode() {
                return Objects.hash(docEntry, lineNum);
            }
        }
    }

}