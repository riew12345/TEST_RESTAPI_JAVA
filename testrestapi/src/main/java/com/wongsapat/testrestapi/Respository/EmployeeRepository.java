package com.wongsapat.testrestapi.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wongsapat.testrestapi.Models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}