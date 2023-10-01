package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private String refId;

    private String employeeNIC;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Role employeeRoleId;

    private String employeeName;

    private Integer employeeAge;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Gender employeeGenderId;

    private List<String> employeePhoneList;

    private String employeeEmail;

    private String employeeAddress;

    private float employeeBasicSalary;

    private Integer employeeRequiredDailyHours;

    private List<String> employeeSkillList;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
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

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public Gender getEmployeeGenderId() {
        return employeeGenderId;
    }

    public void setEmployeeGenderId(Gender employeeGenderId) {
        this.employeeGenderId = employeeGenderId;
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




//    private List<String> employeeList;
}
