package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentRequest (

        LocalDate date,
        LocalTime time,
        String clientRefId,
        String employeeRefId,
        String billRefId,

        String petRefId,
        Status status,
        String notes

){
}
