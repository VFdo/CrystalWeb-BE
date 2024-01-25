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

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "appointment")
public class Appointment extends SerializableObject {

    @NotNull(message = "Check in time is Required")
    @JsonFormat(pattern = DateFormats.LOCAL_DATE_TIME)
    private LocalDateTime date;

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
