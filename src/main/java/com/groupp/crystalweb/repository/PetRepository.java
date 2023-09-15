package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Demo;
import com.groupp.crystalweb.entity.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, String> {
}
