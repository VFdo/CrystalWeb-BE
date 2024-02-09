package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.AttendanceRequest;
import com.groupp.crystalweb.entity.Attendance;
import com.groupp.crystalweb.repository.AttendanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance saveAttendance(AttendanceRequest attendanceRequest){
        Attendance newAttendance = new Attendance(
                attendanceRequest.refId(),
                attendanceRequest.employeeRefId(),
                attendanceRequest.date(),
                attendanceRequest.inTime(),
                attendanceRequest.outTime(),
                attendanceRequest.overTimeHours(),
                attendanceRequest.notes()
        );
        return attendanceRepository.save(newAttendance);
    }

    public Attendance update(String id,AttendanceRequest attendanceRequest) {
        Optional<Attendance> attendance = attendanceRepository.findByRefId(id);
        if (attendance.isPresent()) {
            Attendance existingAttendance = attendance.get();
            existingAttendance.setEmployeeRefId(attendanceRequest.employeeRefId());
            existingAttendance.setDate(attendanceRequest.date());
            existingAttendance.setInTime(attendanceRequest.inTime());
            existingAttendance.setOutTime(attendanceRequest.outTime());
            existingAttendance.setOverTimeHours(attendanceRequest.overTimeHours());
            existingAttendance.setNotes(attendanceRequest.notes());
            return attendanceRepository.save(existingAttendance);
        } else {
            log.info("Attendance not found for refId ", attendanceRequest.refId());
            return null;
        }
    }



    public Attendance getAttendance(String id) {
        Optional<Attendance> attendance = attendanceRepository.findByRefId(id);
        if(attendance.isPresent()){
            return attendance.get();
        }
        return null;
    }

    public List<Attendance> getAllAttendance(){
        return (List<Attendance>) attendanceRepository.findAll();
    }


    public long deleteAttendance(String id){
        return attendanceRepository.deleteByRefId(id);
    }
}
