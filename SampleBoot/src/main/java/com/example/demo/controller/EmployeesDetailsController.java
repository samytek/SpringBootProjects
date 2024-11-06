package com.example.demo.controller;

import com.example.demo.service.DeptDetailsService;
import com.example.demo.service.EmployeesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class EmployeesDetailsController {

    @Autowired
    DeptDetailsService deptDetailsService;

    @Autowired
    private EmployeesDetailsService employeesDetailsService;

    @PostMapping("/uploadcsv")
    public String uploadCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
            String fileName = file.getOriginalFilename();
            String rootPath = "C:/Users/Asus/OneDrive/Desktop/UploadedFiles";
            String absoluteFilePath = rootPath + File.separator + fileName;
            File csvFile = new File(absoluteFilePath);
            file.transferTo(csvFile);
            employeesDetailsService.processCsvFile(csvFile);
            return "CSV processing started";
    }

    @PostMapping("/addDummyEmployees")
    public void addDummyEmployees() {
        employeesDetailsService.saveEmployees();
    }

    @PostMapping("/saveDeptMaster")
    public void saveDeptMaster() throws IOException {
        deptDetailsService.saveDeptMaster();
    }
}
