package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
    Optional<Pet> findByRefId(String refId);
    long deleteByRefId(String refId);
}
