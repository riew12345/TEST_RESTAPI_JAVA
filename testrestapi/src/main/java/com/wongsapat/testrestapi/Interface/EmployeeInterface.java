package com.wongsapat.testrestapi.Interface;

import java.util.List;

import com.wongsapat.testrestapi.Models.Employee;

public interface EmployeeInterface {
    Employee insertEmployee(Employee employee);
    List<Employee> findAllEmployees();
    Employee findById(Integer id);
    void deleteEmployee(Integer id);
}
