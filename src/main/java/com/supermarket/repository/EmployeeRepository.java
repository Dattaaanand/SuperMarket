package com.supermarket.repository;

import com.supermarket.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByRoleIgnoreCaseAndIsActiveTrue(String role);
    List<Employee> findAllByOrderByFirstNameAsc();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
