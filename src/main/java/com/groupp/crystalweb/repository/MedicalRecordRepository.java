package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,String> {
    Optional<MedicalRecord> findByRefId(String medicalRecordRefId);
    long deleteByRefId(String medicalRecordRefId);
}
