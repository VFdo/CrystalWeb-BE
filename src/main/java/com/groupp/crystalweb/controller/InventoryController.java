package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.InventoryRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Inventory;
import com.groupp.crystalweb.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

//    save inventory
    @PostMapping("inventory")
    public ResponseEntity<ApiResponse> saveInventory(@Valid @RequestBody InventoryRequest inventoryRequest){
        Inventory savedInventory =  inventoryService.saveInventory(inventoryRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedInventory
        );
        return ResponseEntity.ok(response);
    }

//    update inventory
    @PutMapping("inventory/{id}")
    public ResponseEntity<ApiResponse> updateInventory(@PathVariable String id, @RequestBody InventoryRequest inventoryRequest){
        Inventory updatedInventory = inventoryService.updateInventory(id, inventoryRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedInventory
        );
        return ResponseEntity.ok(response);
    }

//    find inventory by id
    @GetMapping("inventory/{id}")
    public ResponseEntity<ApiResponse> findInventory(@PathVariable String id){
        Inventory existingInventory = inventoryService.getInventory(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingInventory
        );
        return ResponseEntity.ok(response);
    }

//    get all inventorys
    @GetMapping("/inventory")
    public ResponseEntity<ApiResponse> getInventorys(@RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allInventorys = inventoryService.getAllInventorys(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allInventorys.first(),
                (PageInfo) allInventorys.second()
        );
        return ResponseEntity.ok(response);
    }

//    delete inventory
    @DeleteMapping("inventory/delete/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable String id){
        long deleted = inventoryService.deleteInventory(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Inventory deleted successfully");
        } else {
            response.setPayload("Inventory not found");
        }
        return ResponseEntity.ok(response);
    }
}
