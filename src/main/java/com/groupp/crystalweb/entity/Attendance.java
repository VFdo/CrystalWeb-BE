package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance extends SerializableObject {

    //private String refId;
    //@NotBlank(message = "Employee ID is required")
    private Employee employee;

    @NotNull(message = "Check in time is Required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime inTime;

    @NotNull(message = "Check out time is Required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime outTime;


    private int overTimeHours;

    private String notes;
}
