package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {
    Optional<Pet> findByRefId(String refId);
    long deleteByRefId(String refId);
    @Query("SELECT p FROM Pet p WHERE p.client = :client")
    List<Pet> findByClient(@Param("client") Client client);
}
