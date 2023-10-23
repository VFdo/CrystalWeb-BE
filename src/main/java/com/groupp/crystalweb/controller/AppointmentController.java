package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.AppointmentRequest;
import com.groupp.crystalweb.entity.Appointment;
import com.groupp.crystalweb.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AppointmentController {



        private final AppointmentService appointmentService;

        public AppointmentController(AppointmentService appointmentService) {
            this.appointmentService = appointmentService;
        }



        //    save user
        @PostMapping("/appointment")
        public Appointment saveAppointment(@RequestBody AppointmentRequest appointmentRequest){
            return appointmentService.saveAppointment(appointmentRequest);
        }

        //    get all users
        @GetMapping("/appointment")
        public List<Appointment> getAppointment(){
            return appointmentService.getAllAppointments();

        }

        //    get by id
        @GetMapping("/appointment/{id}")
        public Appointment getAppointment(@PathVariable String refId){
            return appointmentService.getAppointment(refId);
        }

        //    update by id
        @PutMapping("appointment/{refId}")
        public Appointment updateAppointment(@PathVariable String refId, @RequestBody AppointmentRequest appointmentRequest){
            return appointmentService.update(refId, appointmentRequest); //?
        }

        //    delete by id
        @DeleteMapping("user/delete/{refId}")
        public String deleteAppointment(@PathVariable String refId){
            long deleted = appointmentService.deleteAppointment(refId);
            if(deleted != 0){
                return ("Appointment deleted successfully");
            }
            return "Appointment not found!";
        }

}
