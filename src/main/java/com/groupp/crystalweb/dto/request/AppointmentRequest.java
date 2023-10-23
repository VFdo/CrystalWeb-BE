package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Status;

import java.time.LocalDate;

public record AppointmentRequest (
        String refId,
        LocalDate date,
        String clientRefId,
        String employeeRefId,
        String billRefId,
        Status status,
        String notes

){
}
