package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.EmployeeRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        Employee savedEmployee =  employeeService.saveEmployee(employeeRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedEmployee
        );
        return ResponseEntity.ok(response);
    }

    //    update employee
    @PutMapping("employee/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest){
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedEmployee
        );
        return ResponseEntity.ok(response);
    }

    //    find employee by id
    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse> findEmployee(@PathVariable String id){
        Employee existingEmployee = employeeService.getEmployee(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingEmployee
        );
        return ResponseEntity.ok(response);
    }

    //    get all employees
    @GetMapping("/employee")
    public ResponseEntity<ApiResponse> getEmployees(@RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allEmployees = employeeService.getAllEmployees(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allEmployees.first(),
                (PageInfo) allEmployees.second()
        );
        return ResponseEntity.ok(response);
    }


    //    delete employee
    @DeleteMapping("employee/delete/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String id){
        long deleted = employeeService.deleteEmployee(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Employee deleted successfully");
        } else {
            response.setPayload("Employee not found");
        }
        return ResponseEntity.ok(response);
    }

}
