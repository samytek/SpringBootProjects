package com.example.demo.repository;

import com.example.demo.entity.ProcessComplexJsonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessComplexJsonDataRepository extends JpaRepository<ProcessComplexJsonData, Integer> {
}
