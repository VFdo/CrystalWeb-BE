package com.groupp.crystalweb.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AttendanceRequest(
        String refId,
        String employeeRefId,
        LocalDate date,
        LocalDateTime inTime,
        LocalDateTime outTime,
        int overTimeHours,
        String notes
) {
}
