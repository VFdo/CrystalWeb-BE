package com.groupp.crystalweb.repository;

import com.groupp.crystalweb.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Optional<Employee> findByRefId(String refId);
    long deleteByRefId(String refId);
}
