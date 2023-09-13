package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends CrudRepository<Demo, String> {
       Optional<Demo> findByRefId(String refId);
       long deleteByRefId(String refId);
}
