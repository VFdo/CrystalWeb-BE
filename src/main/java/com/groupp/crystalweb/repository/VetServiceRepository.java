package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.VetService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetServiceRepository extends CrudRepository<VetService, String> {
    Optional<VetService> findByRefId(String refId);
    long deleteByRefId(String refId);
}
