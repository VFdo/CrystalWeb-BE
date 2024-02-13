package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.ClientRequest;
import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.repository.ClientRepository;
import com.groupp.crystalweb.repository.PetRepository;
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
@RequiredArgsConstructor
@Slf4j
public class PetService {
    private final PetRepository petRepository;
    private final ClientRepository clientRepository;

//    creating new pet
    public Pet savePet(PetRequest petRequest) {
        try{
            Optional<Client> client = clientRepository.findByRefId(petRequest.clientRefId());
            Pet newPet = new Pet();
            newPet.setName(petRequest.name());
            newPet.setDob(petRequest.dob());
            newPet.setTypeOfAnimal(petRequest.typeOfAnimal());
            newPet.setGender(petRequest.gender());
            newPet.setPhoto(petRequest.photo());
            if(client.isPresent()){
                newPet.setClient(client.get());
            }
            return petRepository.save(newPet);
        } catch (Exception e){
            log.info("pet saving failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }

    }

//    updating existing pet
    public Pet update(String id, PetRequest petRequest) {
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            Optional<Client> client = clientRepository.findByRefId(petRequest.clientRefId());
            Pet existingPet = pet.get();
            existingPet.setName(petRequest.name());
            existingPet.setDob(petRequest.dob());
            existingPet.setTypeOfAnimal(petRequest.typeOfAnimal());
            existingPet.setGender(petRequest.gender());
            existingPet.setPhoto(petRequest.photo());
            if(client.isPresent()){
                existingPet.setClient(client.get());
            }
            return petRepository.save(existingPet);
        } else {
            log.info("Pet not found for id: {}", id);
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

    public Tuple<Object, Object> getAllPets(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Pet> petPage = petRepository.findAll(pageable);
        List<Pet> pets = petPage.getContent();
        PageInfo pageInfo = new PageInfo(
                petPage.getNumber(),
                petPage.getSize(),
                petPage.getTotalElements(),
                petPage.getTotalPages());
        return new Tuple<>(pets, pageInfo);
    }

    public List<Pet> getAllPetsOfClient(String clientRefId) {
        Optional<Client> clientFound = clientRepository.findByRefId(clientRefId);
        Client client = null;
        if(clientFound.isPresent()){
            client = clientFound.get();
        }
        return petRepository.findByClient(client);
    }

    public long deletePet(String id) {
        return petRepository.deleteByRefId(id);
    }
}
