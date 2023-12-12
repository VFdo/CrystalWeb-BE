package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.AppointmentRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Appointment;
import com.groupp.crystalweb.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<ApiResponse> saveAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest){
            Appointment savedAppointment = appointmentService.saveAppointment(appointmentRequest);
            ApiResponse response = new ApiResponse(
                    200,
                    "Success",
                    savedAppointment
            );
            return ResponseEntity.ok(response);
        }

        //    get all users
        @GetMapping("/appointment")
        public ResponseEntity<ApiResponse> getAppointment(@RequestParam(defaultValue = "0") int pageNumber,
                                                          @RequestParam(defaultValue = "10") int pageSize){
            Tuple<Object, Object> allAppointment = appointmentService.getAllAppointments(pageNumber,pageSize);

            ApiResponse response = new ApiResponse(
                    200,
                    "Success",
                    allAppointment.first(),
                    (PageInfo) allAppointment.second()
            );
            return ResponseEntity.ok(response);

        }

        //    get by id
        @GetMapping("/appointment/{id}")
        public ResponseEntity<ApiResponse> findAppointment(@PathVariable String id){
            Appointment existingAppointment = appointmentService.getAppointment(id);
            ApiResponse response = new ApiResponse(
                    200,
                    "Success",
                    existingAppointment
            );
            return ResponseEntity.ok(response);
        }











        //    update by id
        @PutMapping("appointment/{id}")
        public ResponseEntity<ApiResponse> updateAppointment(@PathVariable String id, @RequestBody AppointmentRequest appointmentRequest){
            Appointment updatedAppointment = appointmentService.updateAppointment(id,appointmentRequest);
            ApiResponse response = new ApiResponse(
                    200,
                    "Success",
                    updatedAppointment
            );
            return ResponseEntity.ok(response);
        }

        //    delete by id
        @DeleteMapping("appointment/delete/{id}")
        public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable String id){
            long deleted = appointmentService.deleteAppointment(id);
            ApiResponse response = new ApiResponse();
            response.setStatus(200);
            response.setMessage("Success");
            if(deleted != 0){
                response.setPayload("Appointment deleted successfully");
            }else{
                response.setPayload(("Appointment not found"));
            }
            return ResponseEntity.ok(response);
        }

}
