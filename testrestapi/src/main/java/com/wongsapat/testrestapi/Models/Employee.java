package com.wongsapat.testrestapi.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private String code;
    private String firstname;
    private String lastname;

    public Employee() {
    }

    public Employee(int Id,String Code, String FirstName, String LastName) {
        this.id = Id;
        this.code = Code;
        this.firstname = FirstName;
        this.lastname = LastName;
    }

    public int getId() {
        return id;
    }
    public void setId(int Id) {
        id = Id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String Code) {
        code = Code;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String Firstname) {
        firstname = Firstname;
    }

    public String getLastName() {
        return lastname;
    }
    public void setLastName(String LastName) {
        lastname = LastName;
    }
}
