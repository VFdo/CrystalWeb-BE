package com.groupp.crystalweb.component;


import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.entity.Inventory;
import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.entity.Role;
import com.groupp.crystalweb.service.*;
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

    private final InventoryService inventoryService;

    public EmailScheduler(MedicalRecordService medicalRecordService , EmailSenderService emailSenderService, EmployeeService employeeService,ClientService clientService,InventoryService inventoryService){
        this.medicalRecordService = medicalRecordService;
        this.emailSenderService = emailSenderService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.inventoryService = inventoryService;

    }
    @Scheduled(cron = "0 4 16 * * *") //every day at 12.01AM
    public void sendScheduledReminders(){
        // Retrieve medical records with page information
        Tuple<Object, Object> medicalRecordsTuple = medicalRecordService.getAllMedicalRecords(0, 10);

        // Extract medical records directly without casting
        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) medicalRecordsTuple.first();
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

              emailSenderService.sendEmail(null,vetEmail,vetEmail,subject,body);
                emailSenderService.sendEmail(null,vetEmail,vetEmail,subject,body);

            }
        }
    }

    @Scheduled(cron = "0 1 0 * * *") //every day at 12.01AM
    public void sendInventoryReminders(){
        LocalDate currentDate = LocalDate.now();

        Tuple<Object,Object> inventoryTuple = inventoryService.getAllInventorys(0,10);
        List<Inventory> inventories = (List<Inventory>) inventoryTuple.first();

        Tuple<Object,Object> employeeTuple = employeeService.getAllEmployees(0,10);
        List<Employee> employees = (List<Employee>) employeeTuple.first();

        String adminEmail = employees.stream()
                .filter(e -> e.getEmployeeRoleId().equals(Role.ADMIN))
                .findFirst()
                .map(Employee::getEmployeeEmail)
                .orElse(null);



        for(Inventory i:inventories){
            if(i.getAvaQuantity()<10){
                //String adminEmail = "janithkulatunge@gmail.com";
                String subject = "Inventory stocks low Alert";
                String body = "Inventory of "+i.getName()+" stocks are less than 10 units. Recommend to purchase new stocks ";
               emailSenderService.sendEmail(null,adminEmail,adminEmail,subject,body);

            }
        }
    }
}
