package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "appointment")
public class Appointment extends SerializableObject {

    @NotNull(message = "Name is Required")
    private String name;
    @NotNull(message = "Name is Required")

    private String email;
    @NotNull(message = "Name is Required")

    private String phoneNo;

    @NotNull(message = "Check in time is Required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;


    @JsonFormat(pattern = DateFormats.LOCAL_DATE_TIME)
    private LocalDateTime time;

    @NotNull(message = "Check in time is Required")
    private int noOfPets;

    @NotNull(message = "Client ID must be present")
    @ManyToOne
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToOne
    private Bill bill;

    @ManyToOne
    @JsonIgnore
    private Pet pet;

    private Status status;
    private String notes;

}
