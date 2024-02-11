package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Gender;
import com.groupp.crystalweb.entity.Role;

import java.time.LocalDate;
import java.util.List;

public record EmployeeRequest(
        String employeeNIC,
        Role employeeRoleId,
        String employeeName,
        LocalDate employeeDob,
        Gender employeeGender,
        List<String> employeePhoneList,
        String employeeEmail,
        String employeeAddress,
        float employeeBasicSalary,
        Integer employeeRequiredDailyHours,
        List<String> employeeSkillList
//        List<String>demoList
){

}

