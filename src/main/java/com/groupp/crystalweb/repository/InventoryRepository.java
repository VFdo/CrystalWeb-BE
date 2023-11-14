package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, String> {
    Optional<Inventory> findByRefId(String refId);
    long deleteByRefId(String refId);
}
