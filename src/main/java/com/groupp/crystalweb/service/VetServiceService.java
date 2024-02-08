package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.VetServiceRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.entity.VetService;
import com.groupp.crystalweb.repository.VetServiceRepository;
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
public class VetServiceService {
    private final VetServiceRepository vetServiceRepository;

    public VetServiceService(VetServiceRepository vetServiceRepository) {
        this.vetServiceRepository = vetServiceRepository;
    }

//    creating new vetService
    public VetService saveVetService(VetServiceRequest vetServiceRequest) {
        try{
            VetService newVetService = new VetService();
            newVetService.setName(vetServiceRequest.name());
            newVetService.setChargeByTime(vetServiceRequest.chargeByTime());
            newVetService.setDurationMeasure(vetServiceRequest.durationMeasure());
            newVetService.setAmount(vetServiceRequest.amount());
            return vetServiceRepository.save(newVetService);
        } catch (Exception e){
            log.info("Service saving failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }
    }

//    updating existing vetService
    public VetService update(String id, VetServiceRequest vetServiceRequest) {
        Optional<VetService> vetService = vetServiceRepository.findById(id);
        if(vetService.isPresent()){
            VetService existingVetService = vetService.get();
            existingVetService.setName(vetServiceRequest.name());
            existingVetService.setChargeByTime(vetServiceRequest.chargeByTime());
            existingVetService.setDurationMeasure(vetServiceRequest.durationMeasure());
            existingVetService.setAmount(vetServiceRequest.amount());
            return vetServiceRepository.save(existingVetService);
        } else {
            log.info("Vet Service not found for id: {}",id);
            return null;
        }
    }

    public VetService getVetService(String id) {
        Optional<VetService> vetService = vetServiceRepository.findByRefId(id);
        if(vetService.isPresent()){
            return vetService.get();
        }
        return null;
    }

    public Tuple<Object, Object> getAllVetServices(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VetService> vetServicePage = vetServiceRepository.findAll(pageable);
        List<VetService> vetServices = vetServicePage.getContent();
        PageInfo pageInfo = new PageInfo(
                vetServicePage.getNumber(),
                vetServicePage.getSize(),
                vetServicePage.getTotalElements(),
                vetServicePage.getTotalPages());
        return new Tuple<>(vetServices, pageInfo);
    }

    public long deleteVetService(String id) {
        return vetServiceRepository.deleteByRefId(id);
    }
}
