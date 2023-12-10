package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.EmployeeRequest;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    //    save employee
    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.saveEmployee(employeeRequest);
        return (employee);
    }

    //    update employee
    @PutMapping("employee/{id}")
    public Employee updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.update(id, employeeRequest);
        return employee;
    }

    //    find employee by id
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable String id){
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    //    get all employees
    @GetMapping("/employee")
    public List<Employee> getEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }


    //    delete employee
    @DeleteMapping("employee/delete/{id}")
    public String deleteEmployee(@PathVariable String id){
        long deleted = employeeService.deleteEmployee(id);
        if(deleted != 0){
            return ("Employee deleted successfully");
        }
        return "Employee not found!";
    }

}
