package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Appointment;
import com.groupp.crystalweb.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,String> {
    Optional<Attendance> findByRefId(String refId);
    long deleteByRefId(String refId);
}
