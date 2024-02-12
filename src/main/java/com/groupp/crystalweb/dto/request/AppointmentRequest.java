package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentRequest (
        String name,
        String email,
        String phoneNo,

        LocalDate date,
        LocalTime time,
        int noOfPets
//        String clientRefId,
//        String employeeRefId,
//        String billRefId,
//
//        String petRefId,
//        Status status,
//        String notes

){
}
