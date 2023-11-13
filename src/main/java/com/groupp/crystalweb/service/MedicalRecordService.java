package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.MedicalRecordRequest;
import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.entity.MedicalRecord;
import com.groupp.crystalweb.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Slf4j
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord saveMedicalRecord(MedicalRecordRequest medicalRecordRequest){
        MedicalRecord newMedicalRecord = new MedicalRecord(
                medicalRecordRequest.refId(),
                medicalRecordRequest.date(),
                medicalRecordRequest.petRefId(),
                medicalRecordRequest.vetRefId(),
                medicalRecordRequest.docs(),
                medicalRecordRequest.reminderDate(),
                medicalRecordRequest.notes(),
                medicalRecordRequest.reminderType()
        );
        return medicalRecordRepository.save(newMedicalRecord);
    }

    public List<MedicalRecord> getAllMedicalRecords() {

        return (List<MedicalRecord>) medicalRecordRepository.findAll();
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
            existingMedicalRecord.setRefId(medicalRecordRequest.refId());
            existingMedicalRecord.setDate(medicalRecordRequest.date());
            existingMedicalRecord.setPetRefId(medicalRecordRequest.petRefId());
            existingMedicalRecord.setVetRefId(medicalRecordRequest.vetRefId());
            existingMedicalRecord.setDocs(medicalRecordRequest.docs());
            existingMedicalRecord.setReminderType(medicalRecordRequest.reminderType());
            existingMedicalRecord.setReminderDate(medicalRecordRequest.reminderDate());
            existingMedicalRecord.setNotes(medicalRecordRequest.notes());

            return medicalRecordRepository.save(existingMedicalRecord);
        }
        log.info("MedicalRecord not found for id: P{}", medicalRecordRequest.refId());
        return null;
    }

    public long deleteMedicalRecord(String refId) {

        return medicalRecordRepository.deleteByRefId(refId);
    }
}
