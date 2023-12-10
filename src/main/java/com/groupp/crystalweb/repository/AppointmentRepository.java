package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String> {
    Optional<Appointment> findByRefId(String appointmentRefId);
    long deleteByRefId(String appointmentRefId);
}
