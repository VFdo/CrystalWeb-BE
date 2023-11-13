package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

//    creating new pet
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

//    updating existing pet
    public Pet update(String id, PetRequest petRequest) {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            Pet existingPet = pet.get();
            existingPet.setName(petRequest.name());
            existingPet.setDob(petRequest.dob());
            existingPet.setTypeOfAnimal(petRequest.typeOfAnimal());
            existingPet.setPhoto(petRequest.photo());
            return petRepository.save(existingPet);
        } else {
            log.info("Pet not found for id: P{}", petRequest.refId());
            return null;
        }
    }
    public Pet getPet(String id) {
        Optional<Pet> pet = petRepository.findByRefId(id);
        if(pet.isPresent()){
            return pet.get();
        }
        return null;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public long deletePet(String id) {
        return petRepository.deleteByRefId(id);
    }
}
