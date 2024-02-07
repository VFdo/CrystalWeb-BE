package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.EmployeeRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    // creating new employee
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        log.info("employee save request received");
        try {
            Employee newEmployee = new Employee();
            newEmployee.setEmployeeNIC(employeeRequest.employeeNIC());
            newEmployee.setEmployeeRoleId(employeeRequest.employeeRoleId());
            newEmployee.setEmployeeName(employeeRequest.employeeName());
            newEmployee.setEmployeeAge(employeeRequest.employeeDob());
            newEmployee.setEmployeeGender(employeeRequest.employeeGender());
            newEmployee.setEmployeePhoneList(employeeRequest.employeePhoneList());
            newEmployee.setEmployeeEmail(employeeRequest.employeeEmail());
            newEmployee.setEmployeeAddress(employeeRequest.employeeAddress());
            newEmployee.setEmployeeBasicSalary(employeeRequest.employeeBasicSalary());
            newEmployee.setEmployeeRequiredDailyHours(employeeRequest.employeeRequiredDailyHours());
            newEmployee.setEmployeeHourlyPay(employeeRequest.employeeHourlyPay());
            newEmployee.setEmployeeSkillList(employeeRequest.employeeSkillList());
            return employeeRepository.save(newEmployee);
        } catch (Exception e) {
            log.info("employee saving failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }
    }


    public Tuple<Object, Object> getAllEmployees(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<Employee> employees = employeePage.getContent();
        PageInfo pageInfo = new PageInfo(
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages());
        return new Tuple<>(employees, pageInfo);
    }

    public Employee getEmployee(String id) {
        Optional<Employee> employee = employeeRepository.findByRefId(id);
        if(employee.isPresent()){
            return employee.get();
        }
//        TODO: handle response
        return null;
    }

    public Employee updateEmployee(String id, EmployeeRequest employeeRequest) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            Employee existingEmployee = employee.get();
            existingEmployee.setEmployeeNIC(employeeRequest.employeeNIC());
            existingEmployee.setEmployeeRoleId(employeeRequest.employeeRoleId());
            existingEmployee.setEmployeeName(employeeRequest.employeeName());
            existingEmployee.setEmployeeAge(employeeRequest.employeeDob());
            existingEmployee.setEmployeeGender(employeeRequest.employeeGender());
            existingEmployee.setEmployeePhoneList(employeeRequest.employeePhoneList());
            existingEmployee.setEmployeeEmail(employeeRequest.employeeEmail());
            existingEmployee.setEmployeeAddress(employeeRequest.employeeAddress());
            existingEmployee.setEmployeeBasicSalary(employeeRequest.employeeBasicSalary());
            existingEmployee.setEmployeeRequiredDailyHours(employeeRequest.employeeRequiredDailyHours());
            existingEmployee.setEmployeeHourlyPay(employeeRequest.employeeHourlyPay());
            existingEmployee.setEmployeeSkillList(employeeRequest.employeeSkillList());
            return employeeRepository.save(existingEmployee);
        }else{
            log.info("employee not found for id: {}",id);
            return null;
        }
    }

    public long deleteEmployee(String id) {
        return employeeRepository.deleteByRefId(id);
    }


    public float calculateSalary(float employeeOTimeHours ,float employeeHourlyPay, float employeeBasicSalary){
        float overTimePayment = 0;
        float totalSalary = 0;
        try {
            if ((0 <= employeeOTimeHours) && (employeeBasicSalary != 0)) {
                overTimePayment = ((employeeOTimeHours) * (employeeHourlyPay));
                totalSalary = employeeBasicSalary + overTimePayment;
            }

            return totalSalary;
        }
        catch (Exception e) {
            log.info("Salary calculation failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }
    }


}
