package com.groupp.crystalweb.controller;
import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.ClientRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    //  save client
    @PostMapping("client")
    public ResponseEntity<ApiResponse> saveClient(@Valid @RequestBody ClientRequest clientRequest){
        Client savedClient =  clientService.saveclient(clientRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedClient
        );
        return ResponseEntity.ok(response);
    }

    //  update client
    @PutMapping("client/{id}")
    public ResponseEntity<ApiResponse> updateClient(@PathVariable String id, @RequestBody ClientRequest clientRequest){
        Client updatedClient = clientService.updateClient(id, clientRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedClient
        );
        return ResponseEntity.ok(response);
    }

    //  find client by id
    @GetMapping("client/{id}")
    public ResponseEntity<ApiResponse> findClient(@PathVariable String id){
        Client existingClient = clientService.getClient(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingClient
        );
        return ResponseEntity.ok(response);
    }

    //  get all clients
    @GetMapping("/client")
    public ResponseEntity<ApiResponse> getClients(@RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allClients = clientService.getAllClients(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allClients.first(),
                (PageInfo) allClients.second()
        );
        return ResponseEntity.ok(response);
    }

    //  delete client
    @DeleteMapping("client/delete/{id}")
    public ResponseEntity<ApiResponse> deleteClient(@PathVariable String id){
        long deleted = clientService.deleteClient(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Client deleted successfully");
        } else {
            response.setPayload("Client not found");
        }
        return ResponseEntity.ok(response);
    }
}
