package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.response.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.groupp.crystalweb.dto.request.InventoryRequest;
import com.groupp.crystalweb.entity.Inventory;
import com.groupp.crystalweb.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

//    creating new inventory
    public Inventory saveInventory(InventoryRequest inventoryRequest) {
        log.info("inventory save request received");
        try{
            Inventory newInventory = new Inventory();
            newInventory.setName(inventoryRequest.name());
            newInventory.setAvaQuantity(inventoryRequest.avaQuantity());
            newInventory.setRop(inventoryRequest.rop());
            newInventory.setExpDate(inventoryRequest.expDate());
            newInventory.setSupInfo(inventoryRequest.supInfo());
            return inventoryRepository.save(newInventory);
        } catch (Exception e){
            log.info("inventory saving failed: {}, {}", e.getMessage(), inventoryRequest.refId());
            throw new RuntimeException("Something went wrong!");
        }
    }

//    updating existing inventory
    public Inventory updateInventory(String id, InventoryRequest inventoryRequest){
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()){
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

    public Tuple<Object, Object> getAllInventorys(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);
        List<Inventory> inventorys = inventoryPage.getContent();
        PageInfo pageInfo = new PageInfo(
                inventoryPage.getNumber(),
                inventoryPage.getSize(),
                inventoryPage.getTotalElements(),
                inventoryPage.getTotalPages());
        return new Tuple<>(inventorys, pageInfo);
    }

    public long deleteInventory(String id) {
        return inventoryRepository.deleteByRefId(id);
    }
}
