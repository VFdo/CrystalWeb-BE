package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.service.PetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }


    //    save pet
    @PostMapping("pet")
    public Pet savePet(@RequestBody PetRequest petRequest){
        Pet savedPet = petService.savePet(petRequest);
        return savedPet;
    }

//    update pet

//    find pet

//    delete pet
}
