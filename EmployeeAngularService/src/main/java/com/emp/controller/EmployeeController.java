package com.emp.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.entity.Employee;
import com.emp.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by empId
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empId) {
        Optional<Employee> employee = employeeService.getEmployeeById(empId);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create or update an employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Update an existing employee
    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String empId, @RequestBody Employee employee) {
        if (employeeService.getEmployeeById(empId).isPresent()) {
            employee.setEmpId(empId);
            return ResponseEntity.ok(employeeService.saveEmployee(employee));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete an employee
    @DeleteMapping("/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String empId) {
        if (employeeService.getEmployeeById(empId).isPresent()) {
            employeeService.deleteEmployee(empId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
