package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    Optional<Bill> findByRefId(String refId);
    long deleteByRefId(String refId);
}