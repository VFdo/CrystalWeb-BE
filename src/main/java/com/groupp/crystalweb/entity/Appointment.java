package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "appointment")
public class Appointment {
    @Id
    private String refId;
    private LocalDate date;
    private String clientRefId;
    private String employeeRefId;
    private String billRefId;
    private Status status;
    private String notes;

}
