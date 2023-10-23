package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.VetServiceRequest;
import com.groupp.crystalweb.entity.VetService;
import com.groupp.crystalweb.service.VetServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VetServiceController {
    private final VetServiceService vetServiceService;

    public VetServiceController(VetServiceService vetServiceService) {
        this.vetServiceService = vetServiceService;
    }

//    save vetService
    @PostMapping("vetService")
    public VetService saveVetService(@RequestBody VetServiceRequest vetServiceRequest){
        return vetServiceService.saveVetService(vetServiceRequest);
    }

//    update vetService
    @PutMapping("vetService/{id}")
    public VetService updateVetService(@PathVariable String id, @RequestBody VetServiceRequest vetServiceRequest){
        return vetServiceService.update(id, vetServiceRequest);
    }

//    find vetService by id
    @GetMapping("vetService/{id}")
    public VetService findVetService(@PathVariable String id){
        return vetServiceService.getVetService(id);
    }

//    get all vetServices
    @GetMapping("/vetService")
    public List<VetService> getVetServices(){
        return vetServiceService.getAllVetServices();
    }

//    delete vetService
    @DeleteMapping("vetService/delete/{id}")
    public String deleteVetService(@PathVariable String id){
        long deleted = vetServiceService.deleteVetService(id);
        if(deleted != 0){
            return ("Item deleted successfully");
        }
        return "Item not found!";
    }
}
