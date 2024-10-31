package com.example.SpringBootBatchProcessingJPA.Repository;

import com.example.SpringBootBatchProcessingJPA.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
}