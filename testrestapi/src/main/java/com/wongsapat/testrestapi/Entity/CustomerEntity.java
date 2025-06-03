package com.wongsapat.testrestapi.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OCRD")
public class CustomerEntity {

    @Id
    private String cardCode;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public CustomerEntity() {
    }

    public CustomerEntity(String cardCode, String firstName, String lastName, String email, String phone) {
        this.cardCode = cardCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
