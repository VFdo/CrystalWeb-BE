package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.AttendanceRequest;
import com.groupp.crystalweb.entity.Attendance;
import com.groupp.crystalweb.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }
    @PostMapping("attendance")
    public Attendance saveAttendance(@RequestBody AttendanceRequest attendanceRequest ){
        return attendanceService.saveAttendance(attendanceRequest);
    }
    @PutMapping("attendance/{id}")
    public Attendance updateAttendance(@PathVariable String id,@RequestBody AttendanceRequest attendanceRequest){
        return attendanceService.update(id,attendanceRequest);
    }

    @GetMapping("attendance/{attendance}")
    public Attendance findAttendance(@PathVariable String id){
        return attendanceService.getAttendance(id);
    }
    @GetMapping("/attendance")
    public List<Attendance> getAttendanceList(){
        return attendanceService.getAllAttendance();
    }
    @DeleteMapping("attendance/delete/{id}")
    public String deleteAttendance(@PathVariable String id){
        long deleted = attendanceService.deleteAttendance(id);
        if(deleted != 0){
            return ("Attendance Deleted Successfully");
        }
        return "Attendance not found";
    }
}
