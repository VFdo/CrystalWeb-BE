package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Documents;
import com.groupp.crystalweb.entity.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailMessage, String> {
    Optional<EmailMessage> findByRefId(String refId);
    long deleteByRefId(String refId);
}