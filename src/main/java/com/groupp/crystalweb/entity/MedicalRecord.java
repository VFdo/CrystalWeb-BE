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
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    private String refId;
    private LocalDate date;
    private String petRefId;
    private String vetRefId;
    private String docs;
    private LocalDate reminderDate;
    private String notes;
    private ReminderType reminderType;
}
