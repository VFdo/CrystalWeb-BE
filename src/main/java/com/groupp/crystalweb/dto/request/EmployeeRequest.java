package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Gender;
import com.groupp.crystalweb.entity.Role;

import java.util.List;

public record EmployeeRequest(
        String refId,
        String employeeNIC,
        Role employeeRoleId,
        String employeeName,
        Integer employeeAge,
        Gender employeeGenderId,
        List<String> employeePhoneList,
        String employeeEmail,
        String employeeAddress,
        float employeeBasicSalary,
        Integer employeeRequiredDailyHours,
        List<String> employeeSkillList
//        List<String>demoList
){

}

