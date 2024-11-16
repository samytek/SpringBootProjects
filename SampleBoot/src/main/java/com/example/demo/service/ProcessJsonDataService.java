package com.example.demo.service;

import com.example.demo.entity.ProcessComplexJsonData;
import com.example.demo.entity.ProcessJsonData;
import com.example.demo.repository.ProcessComplexJsonDataRepository;
import com.example.demo.repository.ProcessJsonDataRepository;
import com.example.demo.utils.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProcessJsonDataService {

    @Autowired
    ProcessJsonDataRepository processJsonDataRepository;

    @Autowired
    ProcessComplexJsonDataRepository processComplexJsonDataRepository;

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule()); // Register the module
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    public void parseAndSaveJsonFile() throws IOException {
        ObjectMapper objectMapper = getObjectMapper();
        String rootPath = "C:/Users/Asus/OneDrive/Desktop";
        String fileName = "test.json";
        String absoluteFileName = rootPath + File.separator + fileName;
        List<ProcessJsonData> processJsonDataList = objectMapper.readValue(new File(absoluteFileName), new TypeReference<List<ProcessJsonData>>() {
        });
        processJsonDataRepository.saveAll(processJsonDataList);
    }

    public void parseAndSaveComplexJsonFile() throws IOException {
        ObjectMapper objectMapper = getObjectMapper();
        String rootPath = "C:/Users/Asus/OneDrive/Desktop";
        String fileName = "complex.json";
        String absoluteFileName = rootPath + File.separator + fileName;
        List<ProcessComplexJsonData> processComplexJsonDataList = objectMapper.readValue(new File(absoluteFileName), new TypeReference<List<ProcessComplexJsonData>>() {
        });
        processComplexJsonDataRepository.saveAll(processComplexJsonDataList);
    }
}
