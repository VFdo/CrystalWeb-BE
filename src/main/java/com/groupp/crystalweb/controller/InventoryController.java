package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.InventoryRequest;
import com.groupp.crystalweb.entity.Inventory;
import com.groupp.crystalweb.service.InventoryService;
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
    public Inventory saveInventory(@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.saveInventory(inventoryRequest);
    }

//    update inventory
    @PutMapping("inventory/{id}")
    public Inventory updateInventory(@PathVariable String id, @RequestBody InventoryRequest inventoryRequest){
        return inventoryService.update(id, inventoryRequest);
    }

//    find inventory by id
    @GetMapping("inventory/{id}")
    public Inventory findInventory(@PathVariable String id){
        return inventoryService.getInventory(id);
    }

//    get all inventorys
    @GetMapping("/inventory")
    public List<Inventory> getInventorys(){
        return inventoryService.getAllInventorys();
    }

//    delete inventory
    @DeleteMapping("inventory/delete/{id}")
    public String deleteInventory(@PathVariable String id){
        long deleted = inventoryService.deleteInventory(id);
        if(deleted != 0){
            return ("Item deleted successfully");
        }
        return "Item not found!";
    }
}
