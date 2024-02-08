package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.MedicalRecordRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.*;
import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.repository.EmployeeRepository;
import com.groupp.crystalweb.repository.MedicalRecordRepository;
import com.groupp.crystalweb.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;


    public MedicalRecord saveMedicalRecord(MedicalRecordRequest medicalRecordRequest){
        try{
            Optional<Pet> pet = petRepository.findByRefId(medicalRecordRequest.petRefId());
            Optional<Employee> vet = employeeRepository.findByRefId(medicalRecordRequest.vetRefId());
            MedicalRecord newMedicalRecord = new MedicalRecord();
            newMedicalRecord.setDate(medicalRecordRequest.date());
            if(pet.isPresent()){
                newMedicalRecord.setPetRefId(pet.get());
            }
            if(vet.isPresent()){
                newMedicalRecord.setVetRefId(vet.get());
            }
            newMedicalRecord.setDocs(medicalRecordRequest.docs());
            newMedicalRecord.setReminderDate(medicalRecordRequest.reminderDate());
            newMedicalRecord.setTreatment(medicalRecordRequest.treatment());
            newMedicalRecord.setReminderType(medicalRecordRequest.reminderType());
            return medicalRecordRepository.save(newMedicalRecord);
        } catch (Exception e){
            log.info("Medical record saving failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }
        }

    public Tuple<Object, Object> getAllMedicalRecords(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<MedicalRecord> MedRecordPage = medicalRecordRepository.findAll(pageable);
        List<MedicalRecord> medicalRecords = MedRecordPage.getContent();
        PageInfo pageInfo = new PageInfo(
                MedRecordPage.getNumber(),
                MedRecordPage.getSize(),
                MedRecordPage.getTotalElements(),
                MedRecordPage.getTotalPages());
        return new Tuple<>(medicalRecords, pageInfo);
    }

    public MedicalRecord getMedicalRecord(String refId) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByRefId(refId); //?
        if(medicalRecord.isPresent()){
            return medicalRecord.get();
        }
//        TODO: handle response
        return null;
    }

    public MedicalRecord update(String refId, MedicalRecordRequest medicalRecordRequest) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findByRefId(refId);
        if(medicalRecord.isPresent()){
            MedicalRecord existingMedicalRecord = medicalRecord.get();
            Optional<Pet> pet = petRepository.findByRefId(medicalRecordRequest.petRefId());
            Optional<Employee> vet = employeeRepository.findByRefId(medicalRecordRequest.vetRefId());
            existingMedicalRecord.setDate(medicalRecordRequest.date());
            if(pet.isPresent()){
                existingMedicalRecord.setPetRefId(pet.get());
            }
            if(vet.isPresent()){
                existingMedicalRecord.setVetRefId(vet.get());
            }
            existingMedicalRecord.setDocs(medicalRecordRequest.docs());
            existingMedicalRecord.setReminderDate(medicalRecordRequest.reminderDate());
            existingMedicalRecord.setTreatment(medicalRecordRequest.treatment());
            existingMedicalRecord.setReminderType(medicalRecordRequest.reminderType());
            return medicalRecordRepository.save(existingMedicalRecord);}
        log.info("MedicalRecord not found for id: {}", refId);
        return null;
    }

    public long deleteMedicalRecord(String refId) {
        return medicalRecordRepository.deleteByRefId(refId);
    }
}
