package com.wongsapat.testrestapi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wongsapat.testrestapi.Entity.CustomerEntity;
import com.wongsapat.testrestapi.Interface.CustomerInterface;
import com.wongsapat.testrestapi.Respository.CustomerRepository;

@Service
public class CustomerService implements CustomerInterface {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity insertCustomer(CustomerEntity employee) {
        return customerRepository.save(employee);
    }

    @Override
    public List<CustomerEntity> findAllEmployees() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity findById(String id) {
        Optional<CustomerEntity> Customer = customerRepository.findById(id);

        if (Customer.isPresent()) {
            return Customer.get();
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

}
