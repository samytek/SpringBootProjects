package com.example.demo.controller;

import com.example.demo.service.ComplexJsonProcessorThreadService;
import com.example.demo.service.ProcessJsonDataService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class ProcessJsonDataController {

    @Autowired
    ProcessJsonDataService processJsonDataService;

    @Autowired
    ComplexJsonProcessorThreadService complexJsonProcessorThreadService;

    @PostMapping("/parseJsonData")
    public ResponseEntity<String> saveJsonToDatabase() throws IOException {
        JSONObject respJSONObj = new JSONObject();
        processJsonDataService.parseAndSaveJsonFile();
        respJSONObj.put("status", "success");
        respJSONObj.put("message", "Data has been saved successfully");
        return new ResponseEntity<>(respJSONObj.toString(), HttpStatus.OK);
    }

    @PostMapping("/parseComplexJsonData")
    public ResponseEntity<String> saveComplexJsonToDatabase() throws IOException {
        JSONObject respJSONObj = new JSONObject();
        processJsonDataService.parseAndSaveComplexJsonFile();
        respJSONObj.put("status", "success");
        respJSONObj.put("message", "Data has been saved successfully");
        return new ResponseEntity<>(respJSONObj.toString(), HttpStatus.OK);
    }

    @PostMapping("/parseAndSaveComplexJsonFile")
    public ResponseEntity<String> parseAndSaveComplexJsonFile() throws IOException, ExecutionException, InterruptedException {
        JSONObject respJSONObj = new JSONObject();
        complexJsonProcessorThreadService.parseAndSaveComplexJsonFile();
        respJSONObj.put("status", "success");
        respJSONObj.put("message", "Data has been saved successfully");
        return new ResponseEntity<>(respJSONObj.toString(), HttpStatus.OK);
    }
}
