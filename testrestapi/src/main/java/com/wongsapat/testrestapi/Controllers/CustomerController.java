package com.wongsapat.testrestapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wongsapat.testrestapi.Entity.CustomerEntity;
import com.wongsapat.testrestapi.Interface.CustomerInterface;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerInterface customerInterface;

    public CustomerController(CustomerInterface customerInterface) {
        this.customerInterface = customerInterface;
    }

    @PostMapping("/Customer")
    public CustomerEntity insertEmployee(@RequestBody CustomerEntity entity) {
        //entity.setId(0);
        return customerInterface.insertCustomer(entity);
    }

    @GetMapping("/Customer")
    public List<CustomerEntity> getAllEmployee() {
        return customerInterface.findAllEmployees();
    }

    @GetMapping("/Customer/{id}")
    public CustomerEntity getEmployee(@PathVariable String id) {
        CustomerEntity customer = customerInterface.findById(id);
        if (customer != null) {
            return customer;
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    @PutMapping("/Customer/{id}")
    public CustomerEntity updateCustomer(@RequestBody CustomerEntity entity) {
        return customerInterface.insertCustomer(entity);
    }
    
    @DeleteMapping("/Employee/{id}")
    public void deleteCustomer(@PathVariable String id) {
        CustomerEntity employee = customerInterface.findById(id);
        if (employee != null) {
            customerInterface.deleteCustomer(id);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }        
    }
}
