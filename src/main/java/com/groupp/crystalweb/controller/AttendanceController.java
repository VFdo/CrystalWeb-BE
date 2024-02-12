package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.AttendanceRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Attendance;
import com.groupp.crystalweb.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    //Save attendance
    @PostMapping(value = "attendance",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> saveAttendance(@Valid @RequestBody AttendanceRequest attendanceRequest ){
        Attendance savedAttendance = attendanceService.saveAttendance(attendanceRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedAttendance
        );


        return ResponseEntity.ok(response);
    }

    //update

//    @PutMapping("attendance/{id}")
//    public ResponseEntity<ApiResponse> updateAttendance(@PathVariable String id,@RequestBody AttendanceRequest attendanceRequest){
//        Attendance updatedAttendance = attendanceService.updateAttendance(id,attendanceRequest);
//        ApiResponse response = new ApiResponse(
//                200,
//                "Success",
//                updatedAttendance
//        );
//
//        return ResponseEntity.ok(response);
//    }

    //find attendance by id
    @GetMapping("attendance/{id}")
    public ResponseEntity<ApiResponse> findAttendance(@PathVariable String id){
        Attendance existingAttendance = attendanceService.getAttendance(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingAttendance
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/attendance")
    public ResponseEntity<ApiResponse> getAttendance(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object,Object> allAttendance = attendanceService.getAllAttendance(pageNumber,pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                 allAttendance.first(),
                (PageInfo) allAttendance.second()
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("attendance/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAttendance(@PathVariable String id) {
        long deleted = attendanceService.deleteAttendance(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if (deleted != 0) {
            response.setPayload("Attendance Deleted successfully");
        } else {
            response.setPayload("Attendance not found");
        }
        return ResponseEntity.ok(response);
    }
}
