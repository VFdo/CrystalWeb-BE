package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.DocumentsRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Documents;
import com.groupp.crystalweb.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentsService;


    @PostMapping("documents")
    public ResponseEntity<ApiResponse> saveDocuments(@Valid @RequestBody DocumentsRequest documentsRequest){
        Documents savedDocument =  documentsService.saveDocument(documentsRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedDocument
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("documents/{id}")
    public ResponseEntity<ApiResponse> updateDocuments(@PathVariable String id, @RequestBody DocumentsRequest documentsRequest){
        Documents updatedDocument = documentsService.updateDocument(id, documentsRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedDocument
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("documents/{id}")
    public ResponseEntity<ApiResponse> findDocuments(@PathVariable String id){
        Documents existingDocument = documentsService.getDocument(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingDocument
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/documents")
    public ResponseEntity<ApiResponse> getDocuments(@RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allDocuments = documentsService.getAllDocuments(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allDocuments.first(),
                (PageInfo) allDocuments.second()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("documents/delete/{id}")
    public ResponseEntity<ApiResponse> deleteDocuments(@PathVariable String id){
        long deleted = documentsService.deleteDocument(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Document deleted successfully");
        } else {
            response.setPayload("Document not found");
        }
        return ResponseEntity.ok(response);
    }

}
