package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.MedicalRecordRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }



    //    save user
    @PostMapping("/medical-record")
    public ResponseEntity<ApiResponse> saveMedicalRecord(@RequestBody MedicalRecordRequest medicalRecordRequest){
        MedicalRecord savedMedRecord = medicalRecordService.saveMedicalRecord(medicalRecordRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedMedRecord
        );
        return ResponseEntity.ok(response);
    }

    //    get all users
    @GetMapping("/medical-record")
    public ResponseEntity<ApiResponse> getMedicalRecords(@RequestParam(defaultValue = "0") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allMedicalRecords = medicalRecordService.getAllMedicalRecords(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allMedicalRecords.first(),
                (PageInfo) allMedicalRecords.second()
        );
        return ResponseEntity.ok(response);
    }

    //    get by id
    @GetMapping("/medical-record/{id}")
    public ResponseEntity<ApiResponse> getMedicalRecord(@PathVariable String id){
        MedicalRecord existingMedRecord = medicalRecordService.getMedicalRecord(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingMedRecord
        );
        return ResponseEntity.ok(response);
    }

    //    update by id
    @PutMapping("medical-record/{id}")
    public ResponseEntity<ApiResponse> updateMedicalRecord(@PathVariable String id, @RequestBody MedicalRecordRequest medicalRecordRequest){
        MedicalRecord updatedMedRecord = medicalRecordService.update(id, medicalRecordRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedMedRecord
        );
        return ResponseEntity.ok(response);

    }

    //    delete by id
    @DeleteMapping("medical-record/delete/{id}")
    public ResponseEntity<ApiResponse> deleteMedicalRecord(@PathVariable String id) {
        long deleted = medicalRecordService.deleteMedicalRecord(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if (deleted != 0) {
            response.setPayload("Medical record deleted successfully");
        } else {
            response.setPayload("Medical record not found");
        }
        return ResponseEntity.ok(response);
    }
}
