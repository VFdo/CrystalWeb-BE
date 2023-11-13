package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends JpaRepository<Demo, String> {
    Optional<Demo> findByRefId(String refId);
    long deleteByRefId(String refId);
}