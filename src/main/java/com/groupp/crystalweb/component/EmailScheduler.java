package com.groupp.crystalweb.component;


import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.service.ClientService;
import com.groupp.crystalweb.service.EmailSenderService;
import com.groupp.crystalweb.service.EmployeeService;
import com.groupp.crystalweb.service.MedicalRecordService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmailScheduler {
    private final MedicalRecordService medicalRecordService;
    private final EmailSenderService emailSenderService;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    public EmailScheduler(MedicalRecordService medicalRecordService , EmailSenderService emailSenderService, EmployeeService employeeService,ClientService clientService){
        this.medicalRecordService = medicalRecordService;
        this.emailSenderService = emailSenderService;
        this.employeeService = employeeService;
        this.clientService = clientService;

    }
    @Scheduled(cron = "0 1 0 * * *") //every day at 12.01AM

    public void sendScheduledReminders(){
        List<MedicalRecord> medicalRecord = medicalRecordService.getAllMedicalRecords();
        LocalDate currentDate = LocalDate.now();
        for(MedicalRecord m:medicalRecord){
            if(m.getReminderDate().equals(currentDate)){
               String vetEmail = employeeService.getEmployee(m.getVetRefId()).getEmployeeEmail();
               String clientEmail = clientService.getClient(m.getPetRefId()).getEmail();
               String subject = "Reminder"+m.getReminderType().toString();
               String body;
                if ("VACCINE".equals(m.getReminderType().toString())) {
                   body = "The vaccine follow up for the pet"+m.getPetRefId()+"is today";
                }else{
                   body = "The follow up consultation for the pet"+m.getPetRefId()+"is today";
               }
               emailSenderService.sendEmail(null,vetEmail,clientEmail,subject,body);
            }
        }
    }
}
