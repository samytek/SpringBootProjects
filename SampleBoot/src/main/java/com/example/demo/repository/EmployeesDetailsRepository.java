package com.example.demo.repository;

import com.example.demo.entity.EmployeesDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesDetailsRepository extends JpaRepository<EmployeesDetails, Long> {
}