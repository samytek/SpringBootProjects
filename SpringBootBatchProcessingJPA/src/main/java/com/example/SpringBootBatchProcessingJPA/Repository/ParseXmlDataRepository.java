package com.example.SpringBootBatchProcessingJPA.Repository;

import com.example.SpringBootBatchProcessingJPA.XMLEntities.XmlUserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParseXmlDataRepository extends JpaRepository<XmlUserData, Integer> {
}
