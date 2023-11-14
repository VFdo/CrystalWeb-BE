package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.PetStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetstoreRepository extends CrudRepository<PetStore, String> {
    Optional<PetStore> findByRefId(String refId);
    long deleteByRefId(String refId);
}
