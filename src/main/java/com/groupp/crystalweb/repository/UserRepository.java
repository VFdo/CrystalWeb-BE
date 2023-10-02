package com.groupp.crystalweb.repository;


import com.groupp.crystalweb.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
       Optional<User> findByUserId(String userId);
       long deleteByUserId(String userId);
}
