package com.wongsapat.testrestapi.Interface;

import java.util.List;

import com.wongsapat.testrestapi.Entity.CustomerEntity;

public interface CustomerInterface {
    CustomerEntity insertCustomer(CustomerEntity employee);
    List<CustomerEntity> findAllEmployees();
    CustomerEntity findById(String id);
    void deleteCustomer(String id);
}
