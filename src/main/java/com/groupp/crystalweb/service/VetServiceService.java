package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.VetServiceRequest;
import com.groupp.crystalweb.entity.VetService;
import com.groupp.crystalweb.repository.VetServiceRepository;
import lombok.extern.slf4j.Slf4j;
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
        VetService newVetService = new VetService(
                "S" + vetServiceRequest.refId(),
                vetServiceRequest.name(),
                vetServiceRequest.avgTime(),
                vetServiceRequest.amount()
        );
        return vetServiceRepository.save(newVetService);
    }

//    updating existing vetService
    public VetService update(String id, VetServiceRequest vetServiceRequest) {
        Optional<VetService> vetService = vetServiceRepository.findById(id);
        if(vetService.isPresent()){
            VetService existingVetService = vetService.get();
            existingVetService.setName(vetServiceRequest.name());
            existingVetService.setAvgTime(vetServiceRequest.avgTime());
            existingVetService.setAmount(vetServiceRequest.amount());
            return vetServiceRepository.save(existingVetService);
        } else {
            log.info("Vet Service not found for id: S{}", vetServiceRequest.refId());
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

    public List<VetService> getAllVetServices() {
        return (List<VetService>) vetServiceRepository.findAll();
    }

    public long deleteVetService(String id) {
        return vetServiceRepository.deleteByRefId(id);
    }
}
