package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.MedicalRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord,String> {
    Optional<MedicalRecord> findByRefId(String medicalRecordRefId);
    long deleteByRefId(String medicalRecordRefId);
}
