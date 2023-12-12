package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "appointment")
public class Appointment extends SerializableObject {

    @NotNull(message = "Check in time is Required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employee employee;
    @OneToOne
    private Bill bill;

    @ManyToOne
    private Pet pet;

    private Status status;
    private String notes;

}
