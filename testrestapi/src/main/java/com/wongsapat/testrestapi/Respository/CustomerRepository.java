package com.wongsapat.testrestapi.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wongsapat.testrestapi.Entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {


}