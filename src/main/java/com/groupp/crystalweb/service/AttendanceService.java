package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.AttendanceRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Attendance;
import com.groupp.crystalweb.entity.Employee;
import com.groupp.crystalweb.repository.AttendanceRepository;
import com.groupp.crystalweb.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;



    public Attendance saveAttendance(AttendanceRequest attendanceRequest){
        try {
            Optional<Employee> employee = employeeRepository.findByRefId(attendanceRequest.employeeRefId());
            Attendance newAttendance = new Attendance();
            newAttendance.setEmployeeRefId(attendanceRequest.employeeRefId());
            newAttendance.setPassword(attendanceRequest.password());
           newAttendance.setDate(attendanceRequest.date());
            newAttendance.setInTime(attendanceRequest.inTime());
//            if (employee.isPresent()) {
//                newAttendance.setEmployee(employee.get());
//            }
            return attendanceRepository.save(newAttendance);
        }catch(Exception e){
            log.info("Attendance saving failed: {}",e.getMessage());
            throw new RuntimeException("Error");
        }

    }

//    public Attendance updateAttendance(String id,AttendanceRequest attendanceRequest) {
//        Optional<Attendance> attendance = attendanceRepository.findByRefId(id);
//        if (attendance.isPresent()) {
//            Attendance existingAttendance = attendance.get();
//            existingAttendance.setInTime(attendanceRequest.inTime());
//            existingAttendance.setOutTime(attendanceRequest.outTime());
//            existingAttendance.setOverTimeHours(attendanceRequest.overTimeHours());
//            existingAttendance.setNotes(attendanceRequest.notes());
//            return attendanceRepository.save(existingAttendance);
//        } else {
//            log.info("Attendance not found for refId {} ");
//            return null;
//        }
//    }



    public Attendance getAttendance(String id) {
        Optional<Attendance> attendance = attendanceRepository.findByRefId(id);
        if(attendance.isPresent()){
            return attendance.get();
        }
        return null;
    }

    public Tuple<Object, Object> getAllAttendance(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Attendance> attendancePage = attendanceRepository.findAll(pageable);
        List<Attendance> attendance =attendancePage.getContent();
        PageInfo pageInfo = new PageInfo(
                attendancePage.getNumber(),
                attendancePage.getSize(),
                attendancePage.getTotalElements(),
                attendancePage.getTotalPages());
        return new Tuple<>(attendance,pageInfo);

    }

    public long deleteAttendance(String id){
        return attendanceRepository.deleteByRefId(id);
    }
}
