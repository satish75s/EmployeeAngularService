package com.emp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.entity.Employee;
import com.emp.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create or Update Employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by empId
    public Optional<Employee> getEmployeeById(String empId) {
        return employeeRepository.findById(empId);
    }

    // Delete employee by empId
    public void deleteEmployee(String empId) {
        employeeRepository.deleteById(empId);
    }
}
