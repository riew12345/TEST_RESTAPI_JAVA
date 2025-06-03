package com.wongsapat.testrestapi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OITM")
public class ItemMasterDataEntity {
    @Id
    @Column(length = 50)
    private String itemCode;

    @Column(nullable = false)
    private String itemName;

    private Double price;

    public ItemMasterDataEntity() {
    }

    public ItemMasterDataEntity(String itemCode, String itemName, Double price) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
