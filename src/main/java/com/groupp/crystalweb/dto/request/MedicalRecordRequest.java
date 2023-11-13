package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.ReminderType;

import java.time.LocalDate;

public record MedicalRecordRequest(
    String refId,
    LocalDate date,
    String petRefId,
    String vetRefId,
    String docs,
    LocalDate reminderDate,
    String notes,
    ReminderType reminderType)
{

}
