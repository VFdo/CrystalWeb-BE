package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vet_service")
public class VetService extends SerializableObject{
    @NotBlank(message = "Service name is required")
    private String name;

    @NotNull(message = "isChargeByTime is required")
    private Boolean chargeByTime;

//    TODO: convert to enum - not urgent
    @NotBlank(message = "Duration measure is required: hours/days")
    private String durationMeasure; // hours/days

    @NotNull(message = "Amount per duration is required")
    private Float amount;
}
