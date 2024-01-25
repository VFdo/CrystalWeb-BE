package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee extends SerializableObject {

    @NotBlank(message = "Employee NIC Number is required")
    private String employeeNIC;

//    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull(message = "Employee Role ID is required")
    private Role employeeRoleId;

    @NotBlank(message = "Employee Name is required")
    private String employeeName;

    @JsonFormat(pattern=DateFormats.LOCAL_DATE)
    private LocalDate employeeDob;

    @NotNull(message = "Employee Gender is required")
    private Gender employeeGender;

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "employee_phone_numbers", joinColumns = @JoinColumn(name = "employee_id"))
    private List<String> employeePhoneList;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Employee Email is required")
    private String employeeEmail;

    @NotBlank(message = "Employee Address is required")
    private String employeeAddress;

    @NotNull(message = "Employee Basic Salary is required")
    private float employeeBasicSalary;

    @NotNull(message = "Daily Hours Count is required")
    private Integer employeeRequiredDailyHours;

    @Size(min = 0)
    @ElementCollection
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    private List<String> employeeSkillList;

    public String getEmployeeNIC() {
        return employeeNIC;
    }

    public void setEmployeeNIC(String employeeNIC) {
        this.employeeNIC = employeeNIC;
    }

    public Role getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(Role employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeAge(LocalDate employeeDob) {
        this.employeeDob = employeeDob;
    }

    public Gender getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(Gender employeeGender) {
        this.employeeGender = employeeGender;
    }

    public List<String> getEmployeePhoneList() {
        return employeePhoneList;
    }

    public void setEmployeePhoneList(List<String> employeePhoneList) {
        this.employeePhoneList = employeePhoneList;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public float getEmployeeBasicSalary() {
        return employeeBasicSalary;
    }

    public void setEmployeeBasicSalary(float employeeBasicSalary) {
        this.employeeBasicSalary = employeeBasicSalary;
    }

    public Integer getEmployeeRequiredDailyHours() {
        return employeeRequiredDailyHours;
    }

    public void setEmployeeRequiredDailyHours(Integer employeeRequiredDailyHours) {
        this.employeeRequiredDailyHours = employeeRequiredDailyHours;
    }

    public List<String> getEmployeeSkillList() {
        return employeeSkillList;
    }

    public void setEmployeeSkillList(List<String> employeeSkillList) {
        this.employeeSkillList = employeeSkillList;
    }




}
