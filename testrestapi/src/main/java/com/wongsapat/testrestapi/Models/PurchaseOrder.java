package com.wongsapat.testrestapi.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "purchaseorder")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vendorcode;
    private String item;
    private Integer quantity;
    private Double price;
    private Double total;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String VendorCode, String Item, Integer Quantity, Double Price, Double Total) {
        this.vendorcode = VendorCode;
        this.item = Item;
        this.quantity = Quantity;
        this.price = Price;
        this.total = Total;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer Id) {
        id = Id;
    }

    public String getVendorCode() {
        return vendorcode;
    }
    public void setVendorCode(String VendorCode) {
        vendorcode = VendorCode;
    }

    public String getItem() {
        return item;
    }
    public void setItem(String Item) {
        item = Item;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer Quantity) {
        quantity = Quantity;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double Price) {
        price = Price;
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double Total) {
        total = Total;
    }

}
