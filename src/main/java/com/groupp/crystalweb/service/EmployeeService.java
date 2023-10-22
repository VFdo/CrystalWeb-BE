package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.EmployeeRequest;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    // creating new employee
    public Employee saveEmployee(EmployeeRequest employeeRequest){
        Employee newEmployee = new Employee(
                employeeRequest.refId(),
                employeeRequest.employeeNIC(),
                employeeRequest.employeeRoleId(),
                employeeRequest.employeeName(),
                employeeRequest.employeeAge(),
                employeeRequest.employeeGender(),
                employeeRequest.employeePhoneList(),
                employeeRequest.employeeEmail(),
                employeeRequest.employeeAddress(),
                employeeRequest.employeeBasicSalary(),
                employeeRequest.employeeRequiredDailyHours(),
                employeeRequest.employeeSkillList()

//                employeeRequest.employeeList()
        );
        return employeeRepository.save(newEmployee);
    }

    public List<Employee> getAllEmployees() {

        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployee(String id) {
        Optional<Employee> employee = employeeRepository.findByRefId(id);
        if(employee.isPresent()){
            return employee.get();
        }
//        TODO: handle response
        return null;
    }

    public Employee update(String id, EmployeeRequest employeeRequest) {
        Optional<Employee> employee = employeeRepository.findByRefId(id);
        if(employee.isPresent()){
            Employee existingEmployee = employee.get();
            existingEmployee.setEmployeeNIC(employeeRequest.employeeNIC());
            existingEmployee.setEmployeeRoleId(employeeRequest.employeeRoleId());
            existingEmployee.setEmployeeName(employeeRequest.employeeName());
            existingEmployee.setEmployeeAge(employeeRequest.employeeAge());
            existingEmployee.setEmployeeGender(employeeRequest.employeeGender());
            existingEmployee.setEmployeePhoneList(employeeRequest.employeePhoneList());
            existingEmployee.setEmployeeEmail(employeeRequest.employeeEmail());
            existingEmployee.setEmployeeAddress(employeeRequest.employeeAddress());
            existingEmployee.setEmployeeBasicSalary(employeeRequest.employeeBasicSalary());
            existingEmployee.setEmployeeRequiredDailyHours(employeeRequest.employeeRequiredDailyHours());
            existingEmployee.setEmployeeSkillList(employeeRequest.employeeSkillList());

//            existingEmployee.setEmployeeList(employeeRequest.employeeList());
            return employeeRepository.save(existingEmployee);
        }
            return null;
    }

    public long deleteEmployee(String id) {
        return employeeRepository.deleteByRefId(id);
    }

    /*public float calculateSalary(){
        return
    }*/
}
