package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.DocumentsRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.entity.Documents;
import com.groupp.crystalweb.entity.Pet;
import com.groupp.crystalweb.repository.DocumentsRepository;
import com.groupp.crystalweb.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DocumentService {
    private final DocumentsRepository documentsRepository;
    private final PetRepository petRepository;

    public Documents saveDocument(DocumentsRequest documentsRequest) {
        log.info("document save request received");
        try {
            Optional<Pet> pet = petRepository.findByRefId(documentsRequest.petRefId());
            Documents documents = new Documents();
            documents.setDoc(documentsRequest.doc());
            documents.setName(documentsRequest.name());
            if (pet.isPresent()) {
                documents.setPetRefId(pet.get());
            }
            documents.setDocStatus(documentsRequest.docStatus());
            return documentsRepository.save(documents);
        } catch (Exception e) {
            log.info("Document saving failed: {}", e.getMessage());
            throw new RuntimeException("Something went wrong!");
        }

    }

    public Documents updateDocument(String id, DocumentsRequest documentsRequest){
        Optional<Documents> documents = documentsRepository.findById(id);
        if(documents.isPresent()){
            Optional<Pet> pet = petRepository.findByRefId(documentsRequest.petRefId());
            Documents existingDoc = documents.get();
            existingDoc.setDoc(documentsRequest.doc());
            existingDoc.setName(documentsRequest.name());
            if (pet.isPresent()) {
                existingDoc.setPetRefId(pet.get());
            }
            existingDoc.setDocStatus(documentsRequest.docStatus());
            return documentsRepository.save(existingDoc);
        } else{
            log.info("document not found for id: {}", id);
            return null;
        }
    }

    public Documents getDocument(String id){
        Optional<Documents> documents = documentsRepository.findByRefId(id);
        if (documents.isPresent()){
            return documents.get();
        }
        return null;
    }

    public Tuple<Object, Object> getAllDocuments(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Documents> documentsPage = documentsRepository.findAll(pageable);
        List<Documents> documents = documentsPage.getContent();
        PageInfo pageInfo = new PageInfo(
                documentsPage.getNumber(),
                documentsPage.getSize(),
                documentsPage.getTotalElements(),
                documentsPage.getTotalPages());
        return new Tuple<>(documents, pageInfo);
    }

    public long deleteDocument(String id){
        return documentsRepository.deleteByRefId(id);
    }

}
