package com.wongsapat.testrestapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wongsapat.testrestapi.Interface.EmployeeInterface;
import com.wongsapat.testrestapi.Models.Employee;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeInterface employeeInterface;

    public EmployeeController(EmployeeInterface employeeInterface) {
        this.employeeInterface = employeeInterface;
    }

    @PostMapping("/Employee")
    public Employee insertEmployee(@RequestBody Employee entity) {
        entity.setId(0);
        return employeeInterface.insertEmployee(entity);
    }

    @GetMapping("/Employee")
    public List<Employee> getAllEmployee() {
        return employeeInterface.findAllEmployees();
    }

    @GetMapping("/Employee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeInterface.findById(id);
        if (employee != null) {
            return employee;
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    @PutMapping("/Employee/{id}")
    public Employee updateEmployee(@RequestBody Employee entity) {
        return employeeInterface.insertEmployee(entity);
    }
    
    @DeleteMapping("/Employee/{id}")
    public void deleteEmployee(@PathVariable int id) {
        Employee employee = employeeInterface.findById(id);
        if (employee != null) {
            employeeInterface.deleteEmployee(id);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }        
    }
}
