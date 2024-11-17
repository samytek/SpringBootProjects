package com.example.SpringBootBatchProcessingJPA.Repository;

import com.example.SpringBootBatchProcessingJPA.XMLEntities.Devices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends JpaRepository<Devices, Integer> {
}
