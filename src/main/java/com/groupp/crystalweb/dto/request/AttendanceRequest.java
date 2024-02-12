package com.groupp.crystalweb.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AttendanceRequest(

        String employeeRefId,
        String password,

        LocalDate date,
        LocalTime inTime


) {
}
