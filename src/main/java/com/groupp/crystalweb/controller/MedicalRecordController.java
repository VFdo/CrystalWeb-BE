package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.MedicalRecordRequest;
import com.groupp.crystalweb.dto.request.UserRequest;
import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.entity.User;
import com.groupp.crystalweb.service.MedicalRecordService;
import com.groupp.crystalweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }



    //    save user
    @PostMapping("/medicalrecord")
    public MedicalRecord saveMedicalRecord(@RequestBody MedicalRecordRequest medicalRecordRequest){
        return medicalRecordService.saveMedicalRecord(medicalRecordRequest);
    }

    //    get all users
    @GetMapping("/medicalrecord")
    public List<MedicalRecord> getMedicalRecords(){
        return medicalRecordService.getAllMedicalRecords();

    }

    //    get by id
    @GetMapping("/medicalrecord/{id}")
    public MedicalRecord getMedicalRecord(@PathVariable String id){
        return medicalRecordService.getMedicalRecord(id);
    }

    //    update by id
    @PutMapping("medicalrecord/{id}")
    public MedicalRecord updateMedicalRecord(@PathVariable String id, @RequestBody MedicalRecordRequest medicalRecordRequest){
        return medicalRecordService.update(id, medicalRecordRequest); //?
    }

    //    delete by id
    @DeleteMapping("medicalrecord/delete/{id}")
    public String deleteMedicalRecord(@PathVariable String id){
        long deleted = medicalRecordService.deleteMedicalRecord(id);
        if(deleted != 0){
            return ("Medical Record deleted successfully");
        }
        return "Medical Record not found!";
    }
}
