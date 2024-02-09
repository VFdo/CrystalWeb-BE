package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "attendance")
public class Attendance {
    @Id
    private String refId;
    private String employeeRefId;
    private LocalDate date;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private int overTimeHours;
    private String notes;


}
