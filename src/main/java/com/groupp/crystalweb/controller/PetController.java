package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.PetRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.service.PetService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse> savePet(@RequestBody PetRequest petRequest){
        Pet savedPet = petService.savePet(petRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedPet
        );
        return ResponseEntity.ok(response);
    }

//    update pet
    @PutMapping("pet/{id}")
    public ResponseEntity<ApiResponse> updatePet(@PathVariable String id, @RequestBody PetRequest petRequest){
        Pet updatedPet = petService.update(id, petRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedPet
        );
        return ResponseEntity.ok(response);
    }

//    find pet by id
    @GetMapping("pet/{id}")
    public ResponseEntity<ApiResponse> findPet(@PathVariable String id){
        Pet existingPet = petService.getPet(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingPet
        );
        return ResponseEntity.ok(response);
    }

//    get all pets
    @GetMapping("/pet")
    public ResponseEntity<ApiResponse> getPets(@RequestParam(defaultValue = "0") int pageNumber,
                                               @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allPets = petService.getAllPets(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allPets.first(),
                (PageInfo) allPets.second()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pet/client/{id}")
    public ResponseEntity<ApiResponse> getClientPets(@PathVariable String id){
        List<Pet> pets = petService.getAllPetsOfClient(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                pets
        );
        return ResponseEntity.ok(response);
    }

//    delete pet
    @DeleteMapping("pet/delete/{id}")
    public ResponseEntity<ApiResponse> deletePet(@PathVariable String id){
        long deleted = petService.deletePet(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Pet deleted successfully");
        } else {
            response.setPayload("Pet not found");
        }
        return ResponseEntity.ok(response);
    }
}
