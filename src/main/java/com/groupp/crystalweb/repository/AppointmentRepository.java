package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Appointment;
import com.groupp.crystalweb.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository <Appointment,String> {
    Optional<Appointment> findByRefId(String appointmentRefId);
    long deleteByRefId(String appointmentRefId);
}
