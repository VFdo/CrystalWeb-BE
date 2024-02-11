package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Documents;
import com.groupp.crystalweb.entity.ReminderType;

import java.time.LocalDate;
import java.util.List;

public record MedicalRecordRequest(
   LocalDate date,
    String petRefId,
    String vetRefId,
    List<Documents> docs,
    LocalDate reminderDate,
    String treatment,
    ReminderType reminderType)
{

}
