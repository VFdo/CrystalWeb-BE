package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.service.PetService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    //    save pet
    @PostMapping("pet")
    public Pet savePet(@RequestBody PetRequest petRequest){
        return petService.savePet(petRequest);
    }

//    update pet
    @PutMapping("pet/{id}")
    public Pet updatePet(@PathVariable String id, @RequestBody PetRequest petRequest){
        return petService.update(id, petRequest);
    }

//    find pet

//    delete pet
}
