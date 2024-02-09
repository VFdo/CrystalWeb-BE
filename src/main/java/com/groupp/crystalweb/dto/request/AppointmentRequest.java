package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Status;

import java.time.LocalDateTime;

public record AppointmentRequest (

        LocalDateTime date,
        String clientRefId,
        String employeeRefId,
        String billRefId,

        String petRefId,
        Status status,
        String notes

){
}
