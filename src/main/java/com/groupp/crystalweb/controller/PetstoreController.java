package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.PetstoreRequest;
import com.groupp.crystalweb.entity.PetStore;

import com.groupp.crystalweb.service.PetstoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PetstoreController {
    private final PetstoreService petstoreService;

    public PetstoreController(PetstoreService petstoreService) {
        this.petstoreService = petstoreService;
    }

    //    save item
    @PostMapping("item")
    public PetStore saveItem(@RequestBody PetstoreRequest petstoreRequest) {
        return petstoreService.saveItem(petstoreRequest);
    }
    //    update item
    @PutMapping("item/{id}")
    public PetStore updateItem(@PathVariable String id, @RequestBody PetstoreRequest petstoreRequest){
        return petstoreService.update(id, petstoreRequest);
    }

    //    find item by id
    @GetMapping("item/{id}")
    public PetStore findItem(@PathVariable String id){
        return petstoreService.getItem(id);
    }

    //    get all items
    @GetMapping("/item")
    public List<PetStore> getItems(){
        return petstoreService.getAllItems();
    }

    //    delete item
    @DeleteMapping("item/delete/{id}")
    public String deleteItem(@PathVariable String id){
        long deleted = petstoreService.deleteItem(id);
        if(deleted != 0){
            return ("Item deleted successfully");
        }
        return "Item not found!";
    }
}

