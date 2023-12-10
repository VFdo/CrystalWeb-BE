package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.PetstoreRequest;
import com.groupp.crystalweb.entity.PetStore;
import com.groupp.crystalweb.repository.PetstoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PetstoreService {
    private final PetstoreRepository petstoreRepository;

    public PetstoreService(PetstoreRepository petstoreRepository) {
        this.petstoreRepository = petstoreRepository;
    }

    //    creating new item
    public PetStore saveItem(PetstoreRequest petstoreRequest) {
        PetStore newPetstore = new PetStore(
                        "I" + petstoreRequest.refId(),
                        petstoreRequest.name(),
                        petstoreRequest.descrption(),
                        petstoreRequest.unitprice(),
                        petstoreRequest.productid(),
                        petstoreRequest.photo(),
//                        petstoreRequest.catergory()
                null
                );
        return petstoreRepository.save(newPetstore);
    }

    //    updating existing item
    public PetStore update(String id, PetstoreRequest petstoreRequest) {
        Optional<PetStore> item = petstoreRepository.findById(id);
        if(item.isPresent()){
            PetStore existingItem = item.get();
            existingItem.setName(petstoreRequest.name());
            existingItem.setDescription(petstoreRequest.descrption());
            existingItem.setUnitprice(petstoreRequest.unitprice());
            existingItem.setProductid(petstoreRequest.productid());
            existingItem.setPhoto(petstoreRequest.photo());
//            existingItem.setCatergory(petstoreRequest.catergory());
            return petstoreRepository.save(existingItem);
        } else {
            log.info("Item not found for id: I{}", petstoreRequest.refId());
            return null;
        }
    }
    public PetStore getItem(String id) {
        Optional<PetStore> item = petstoreRepository.findByRefId(id);
        if(item.isPresent()){
            return item.get();
        }
        return null;
    }

    public List<PetStore> getAllItems() {
        return (List<PetStore>) petstoreRepository.findAll();
    }

    public long deleteItem(String id) {
        return petstoreRepository.deleteByRefId(id);
    }
}