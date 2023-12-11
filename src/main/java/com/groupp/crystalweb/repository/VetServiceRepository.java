package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.VetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetServiceRepository extends JpaRepository<VetService, String> {
    Optional<VetService> findByRefId(String refId);
    long deleteByRefId(String refId);
}
