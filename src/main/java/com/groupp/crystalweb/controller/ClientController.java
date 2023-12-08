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
    public String deleteClient(@PathVariable String id){
        long deleted = clientService.deleteClient(id);
        if(deleted !=0){
            return ("client deleted successfully");
        }
        return "Client not found!";
    }


}
