package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.InventoryRequest;
import com.groupp.crystalweb.entity.Inventory;
import com.groupp.crystalweb.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

//    creating new inventory
    public Inventory saveInventory(InventoryRequest inventoryRequest) {
        Inventory newInventory = new Inventory(
                "I" + inventoryRequest.refId(),
                inventoryRequest.name(),
                inventoryRequest.avaQuantity(),
                inventoryRequest.rop(),
                inventoryRequest.expDate(),
                inventoryRequest.supInfo()
        );
        return inventoryRepository.save(newInventory);
    }

//    updating existing inventory
    public Inventory update(String id, InventoryRequest inventoryRequest) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if(inventory.isPresent()){
            Inventory existingInventory = inventory.get();
            existingInventory.setName(inventoryRequest.name());
            existingInventory.setAvaQuantity(inventoryRequest.avaQuantity());
            existingInventory.setRop(inventoryRequest.rop());
            existingInventory.setExpDate(inventoryRequest.expDate());
            existingInventory.setSupInfo(inventoryRequest.supInfo());
            return inventoryRepository.save(existingInventory);
        } else {
            log.info("Inventory not found for id: I{}", inventoryRequest.refId());
            return null;
        }
    }
    public Inventory getInventory(String id) {
        Optional<Inventory> inventory = inventoryRepository.findByRefId(id);
        if(inventory.isPresent()){
            return inventory.get();
        }
        return null;
    }

    public List<Inventory> getAllInventorys() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    public long deleteInventory(String id) {
        return inventoryRepository.deleteByRefId(id);
    }
}
