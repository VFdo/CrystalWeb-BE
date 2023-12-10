package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
    Optional<Pet> findByRefId(String refId);
    long deleteByRefId(String refId);
}
