package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.ClientRequest;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    // creating a new client
    public Client saveclient(ClientRequest clientRequest){
        try{
            Client newClient = new Client();
            newClient.setName(clientRequest.name());
            newClient.setNic(clientRequest.nic());
            newClient.setAddress(clientRequest.address());
            newClient.setPhone(clientRequest.phone());
            newClient.setEmail(clientRequest.email());
            newClient.setRole(clientRequest.role());
            return clientRepository.save(newClient);
        } catch (Exception e){
            throw new RuntimeException("Something went wrong!");
        }
    }

// updating existing client
    public Client updateClient(String id, ClientRequest clientRequest){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            Client existingClient = client.get();
            existingClient.setName(clientRequest.name());
            existingClient.setNic(clientRequest.nic());
            existingClient.setAddress(clientRequest.address());
            existingClient.setPhone(clientRequest.phone());
            existingClient.setEmail(clientRequest.email());
            existingClient.setRole(clientRequest.role());

            return clientRepository.save(existingClient);
        } else{
            log.info("client not found for id: p{}",clientRequest.refId());
            return null;
        }
    }
    public Client getClient(String id){
        Optional<Client> client = clientRepository.findByRefId(id);
        if (client.isPresent()){
            return client.get();
        }
        return null;
    }
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public long deleteClient(String id){
        return clientRepository.deleteByRefId(id);
    }
}
