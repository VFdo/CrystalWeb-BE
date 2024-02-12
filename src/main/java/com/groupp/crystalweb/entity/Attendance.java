package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance extends SerializableObject {


    @JsonIgnore
    private String employeeRefId;

    @NotNull(message = "Password is Required")
    private String password;

    @NotNull(message = "Check date is Required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotNull(message = "Check in time is Required")
    @JsonFormat(pattern = DateFormats.LOCAL_TIME)
    private LocalDateTime inTime;

//    @ManyToOne
//    @JsonIgnore
   // private Employee employee;

    //private int overTimeHours;

    //private String notes;
}
