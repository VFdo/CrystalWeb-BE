package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.VetServiceRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.VetService;
import com.groupp.crystalweb.service.VetServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class VetServiceController {
    private final VetServiceService vetServiceService;

    public VetServiceController(VetServiceService vetServiceService) {
        this.vetServiceService = vetServiceService;
    }

//    save vetService
    @PostMapping("service")
    public ResponseEntity<ApiResponse> saveVetService(@RequestBody VetServiceRequest vetServiceRequest){
        VetService vetService = vetServiceService.saveVetService(vetServiceRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                vetService
        );
        return ResponseEntity.ok(response);
    }

//    update vetService
    @PutMapping("service/{id}")
    public ResponseEntity<ApiResponse> updateVetService(@PathVariable String id, @RequestBody VetServiceRequest vetServiceRequest){
        VetService vetService = vetServiceService.update(id, vetServiceRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                vetService
        );
        return ResponseEntity.ok(response);
    }

//    find vetService by id
    @GetMapping("service/{id}")
    public ResponseEntity<ApiResponse> findVetService(@PathVariable String id){
        VetService vetService = vetServiceService.getVetService(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                vetService
        );
        return ResponseEntity.ok(response);
    }

//    get all vetServices
    @GetMapping("/service")
    public ResponseEntity<ApiResponse> getVetServices(@RequestParam(defaultValue = "0") int pageNumber,
                                                      @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allVetServices = vetServiceService.getAllVetServices(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allVetServices.first(),
                (PageInfo) allVetServices.second()
        );
        return ResponseEntity.ok(response);
    }

//    delete vetService
    @DeleteMapping("service/delete/{id}")
    public ResponseEntity<ApiResponse> deleteVetService(@PathVariable String id){
        long deleted = vetServiceService.deleteVetService(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Service deleted successfully");
        } else {
            response.setPayload("Service not found");
        }
        return ResponseEntity.ok(response);
    }
}
