package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(PetRequest petRequest) {
        Pet newPet = new Pet(
                "P" + petRequest.refId(),
                petRequest.name(),
                petRequest.dob(),
                petRequest.typeOfAnimal(),
                petRequest.photo()
        );
        return petRepository.save(newPet);
    }


}
