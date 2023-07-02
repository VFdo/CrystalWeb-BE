package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends MongoRepository<Demo, String> {
       Optional<Demo> findByRefId(String refId);
       long deleteByRefId(String refId);
}
