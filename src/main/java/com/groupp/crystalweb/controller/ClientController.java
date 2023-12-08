package com.groupp.crystalweb.controller;
import com.groupp.crystalweb.dto.request.ClientRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    //  save client
    @PostMapping("client")
    public ResponseEntity<ApiResponse<Client>> saveClient(@RequestBody ClientRequest clientRequest){
        Client savedClient =  clientService.saveclient(clientRequest);
        ApiResponse<Client> response = new ApiResponse<>(
                200,
                "Success",
                savedClient
        );
        return ResponseEntity.ok(response);
    }

    //  update client
    @PutMapping("client/{id}")
    public Client updateClient(@PathVariable String id, @RequestBody ClientRequest clientRequest){
        return clientService.updateClient(id, clientRequest);
    }

    //  find client by id
    @GetMapping("client/{id}")
    public Client findClient(@PathVariable String id){
        return clientService.getClient(id);
    }

    //  get all clients
    @GetMapping("/client")
    public List<Client> getClients(){
        return clientService.getAllClients();
    }

    //  delete client
    @DeleteMapping("client/delete/{id}")
    public String deleteClient(@PathVariable String id){
        long deleted = clientService.deleteClient(id);
        if(deleted !=0){
            return ("client deleted successfully");
        }
        return "Client not found!";
    }


}
