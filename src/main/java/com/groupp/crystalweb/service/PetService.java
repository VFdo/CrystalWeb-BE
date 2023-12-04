package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.ClientRequest;
import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.repository.ClientRepository;
import com.groupp.crystalweb.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PetService {
    private final PetRepository petRepository;
    private final ClientRepository clientRepository;

//    creating new pet
    public Pet savePet(PetRequest petRequest) {
        Optional<Client> client = clientRepository.findByRefId(petRequest.clientRefId());
        Pet newPet = new Pet();
        newPet.setName(petRequest.name());
        newPet.setDob(petRequest.dob());
        newPet.setTypeOfAnimal(petRequest.typeOfAnimal());
        newPet.setPhoto(petRequest.photo());
        if(client.isPresent()){
            newPet.setClient(client.get());
        }
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
