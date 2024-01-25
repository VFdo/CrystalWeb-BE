package com.groupp.crystalweb.service;
import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.AppointmentRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.*;
import com.groupp.crystalweb.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final BillRepository billRepository;
    private final PetRepository petRepository;



    public Appointment saveAppointment(AppointmentRequest appointmentRequest){
        try{
            Appointment newAppointment = new Appointment();
            Optional<Employee> employee = employeeRepository.findByRefId(appointmentRequest.employeeRefId());
            Optional<Client> client = clientRepository.findByRefId(appointmentRequest.clientRefId());
            Optional<Bill> bill = billRepository.findByRefId(appointmentRequest.billRefId());
            Optional<Pet> pet = petRepository.findById(appointmentRequest.petRefId());
            newAppointment.setDate(appointmentRequest.date());
            newAppointment.setStatus(appointmentRequest.status());
            newAppointment.setNotes(appointmentRequest.notes());
            if(employee.isPresent()){
                newAppointment.setEmployee(employee.get());
            }
            if(client.isPresent()){
                newAppointment.setClient(client.get());
            }
            if(bill.isPresent()){
                newAppointment.setBill(bill.get());
            }
            if(pet.isPresent()){
                newAppointment.setPet(pet.get());
            }
            return appointmentRepository.save (newAppointment);
        }catch(Exception e) {
            log.info("Appointment saving failed {}", e.getMessage());
            throw new RuntimeException("Error");
        }
    }

    public Tuple<Object, Object> getAllAppointments(int pageNumber, int pageSize) {
        Pageable pageable =  PageRequest.of(pageNumber,pageSize);
        Page<Appointment> appointmentPage = appointmentRepository.findAll(pageable);
        List<Appointment> appointments = appointmentPage.getContent();
        PageInfo pageInfo = new PageInfo(
                appointmentPage.getNumber(),
                appointmentPage.getSize(),
                appointmentPage.getTotalElements(),
                appointmentPage.getTotalPages());
        return new Tuple<>(appointments,pageInfo);

    }

    public Appointment getAppointment(String refId) {
        Optional<Appointment> appointment = appointmentRepository.findByRefId(refId); //?
        if(appointment.isPresent()){
            return appointment.get();
        }
//        TODO: handle response
        return null;
    }

    public Appointment updateAppointment(String refId, AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointment = appointmentRepository.findByRefId(refId);
        if(appointment.isPresent()){
            Appointment existingAppointment = appointment.get();
            existingAppointment.setDate(appointmentRequest.date());
            existingAppointment.setStatus(appointmentRequest.status());
            existingAppointment.setNotes(appointmentRequest.notes());
            return appointmentRepository.save(existingAppointment);
        }
        log.info("Appointment not found for id: P{}", refId);
        return null;
    }

    public long deleteAppointment(String refId) {

        return appointmentRepository.deleteByRefId(refId);
    }
}
