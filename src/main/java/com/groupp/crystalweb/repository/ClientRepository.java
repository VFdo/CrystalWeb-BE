package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByRefId(String refId);
    long deleteByRefId(String refId);
}
