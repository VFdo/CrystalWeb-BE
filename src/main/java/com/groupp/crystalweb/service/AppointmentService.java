package com.groupp.crystalweb.service;
import com.groupp.crystalweb.dto.request.AppointmentRequest;
import com.groupp.crystalweb.entity.Appointment;
import com.groupp.crystalweb.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment saveAppointment(AppointmentRequest appointmentRequest){
        Appointment newAppointment = new Appointment(
                appointmentRequest.refId(),
                appointmentRequest.date(),
                appointmentRequest.clientRefId(),
                appointmentRequest.employeeRefId(),
                appointmentRequest.billRefId(),
                appointmentRequest.status(),
                appointmentRequest.notes()
        );
        return appointmentRepository.save(newAppointment);
    }

    public List<Appointment> getAllAppointments() {

        return (List<Appointment>) appointmentRepository.findAll();
    }

    public Appointment getAppointment(String refId) {
        Optional<Appointment> appointment = appointmentRepository.findByRefId(refId); //?
        if(appointment.isPresent()){
            return appointment.get();
        }
//        TODO: handle response
        return null;
    }

    public Appointment update(String refId, AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointment = appointmentRepository.findByRefId(refId);
        if(appointment.isPresent()){
            Appointment existingAppointment = appointment.get();
            existingAppointment.setRefId(appointmentRequest.refId());
            existingAppointment.setDate(appointmentRequest.date());
            existingAppointment.setClientRefId(appointmentRequest.clientRefId());
            existingAppointment.setEmployeeRefId(appointmentRequest.employeeRefId());
            existingAppointment.setBillRefId(appointmentRequest.billRefId());
            existingAppointment.setStatus(appointmentRequest.status());
            existingAppointment.setNotes(appointmentRequest.notes());
            return appointmentRepository.save(existingAppointment);
        }
        log.info("Appointment not found for id: P{}", appointmentRequest.refId());
        return null;
    }

    public long deleteAppointment(String refId) {

        return appointmentRepository.deleteByRefId(refId);
    }
}
