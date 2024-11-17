package com.example.SpringBootBatchProcessingJPA.Controller;

import com.example.SpringBootBatchProcessingJPA.Service.ParseXmlDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.xml.bind.JAXBException;

@RestController
public class ParseXmlDataController {

    @Autowired
    ParseXmlDataService parseXmlDataService;

    @PostMapping("/parseAndSave")
    public String parseAndSave() throws JAXBException {
        parseXmlDataService.parseAndSaveUsers();
        return "Users data have been processed successfully!";
    }
}
