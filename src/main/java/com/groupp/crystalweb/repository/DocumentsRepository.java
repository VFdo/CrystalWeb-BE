package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, String> {
    Optional<Documents> findByRefId(String refId);
    long deleteByRefId(String refId);
}
