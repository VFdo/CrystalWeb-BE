package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Order;
import com.groupp.crystalweb.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository <Order,String> {
    Optional<Order> findByRefId(String OrderRefId);
    long deleteByRefId(String OrderRefId);
}