package com.groupp.crystalweb.component;


import com.groupp.crystalweb.common.Tuple;
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
        // Retrieve medical records with page information
        Tuple<Object, Object> medicalRecordsTuple = medicalRecordService.getAllMedicalRecords(0, 10);

        // Extract medical records directly without casting
        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) medicalRecordsTuple.first();;
        LocalDate currentDate = LocalDate.now();
        for(MedicalRecord m:medicalRecords){
            if(m.getReminderDate().equals(currentDate)){
               String vetEmail = employeeService.getEmployee(String.valueOf(m.getVetRefId())).getEmployeeEmail();
               String clientEmail = clientService.getClient(String.valueOf(m.getPetRefId())).getEmail();
               String subject = "Reminder"+m.getReminderType().toString();
               String body;
                if ("VACCINE".equals(m.getReminderType().toString())) {
                   body = "The vaccine follow up for the pet"+m.getPetRefId()+"is today";
                }else{
                   body = "The follow up consultation for the pet"+m.getPetRefId()+"is today";
               }
               emailSenderService.sendEmail(null,vetEmail,null,subject,body);
                emailSenderService.sendEmail(null,clientEmail,null,subject,body);

            }
        }
    }
}
