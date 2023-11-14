package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {
    Optional<Client> findByRefId(String refId);
    long deleteByRefId(String refId);
}
