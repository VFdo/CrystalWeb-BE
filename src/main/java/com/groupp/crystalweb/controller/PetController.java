package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    find pet by id
    @GetMapping("pet/{id}")
    public Pet findPet(@PathVariable String id){
        return petService.getPet(id);
    }

//    get all pets
    @GetMapping("/pet")
    public List<Pet> getPets(){
        return petService.getAllPets();
    }

//    delete pet
    @DeleteMapping("pet/delete/{id}")
    public String deletePet(@PathVariable String id){
        long deleted = petService.deletePet(id);
        if(deleted != 0){
            return ("Item deleted successfully");
        }
        return "Item not found!";
    }
}
