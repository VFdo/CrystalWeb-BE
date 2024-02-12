package com.groupp.crystalweb.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceRequest(

        String employeeRefId,
        String password,

        LocalDate date,
        LocalDateTime inTime


) {
}
