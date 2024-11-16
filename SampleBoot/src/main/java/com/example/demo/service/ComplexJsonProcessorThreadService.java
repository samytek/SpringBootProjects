package com.example.demo.service;

import com.example.demo.entity.ProcessComplexJsonData;
import com.example.demo.repository.ProcessComplexJsonDataRepository;
import com.example.demo.repository.ProcessJsonDataRepository;
import com.example.demo.utils.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ComplexJsonProcessorThreadService {

    public void parseAndSaveComplexJsonFile() throws IOException, InterruptedException, ExecutionException {
        String rootPath = "C:/Users/Asus/OneDrive/Desktop";
        String fileName = "complex.json";
        String absoluteFileName = rootPath + File.separator + fileName;

        ExecutorService executorService = Executors.newFixedThreadPool(4); // Adjust the pool size as needed

        // Split the JSON parsing into chunks
        JsonParsingTask parsingTask = new JsonParsingTask(absoluteFileName);
        Future<List<ProcessComplexJsonData>> parseResult = executorService.submit(parsingTask);

        // Wait for the parsing to complete
        List<ProcessComplexJsonData> processComplexJsonDataList = parseResult.get();

        // Once parsing is done, submit the data saving task
        DataSavingTask savingTask = new DataSavingTask(processComplexJsonDataList);
        executorService.submit(savingTask);

        // Shutdown the executor after all tasks are done
        executorService.shutdown();
    }
}

class JsonParsingTask implements Callable<List<ProcessComplexJsonData>> {
    private final String filePath;

    public JsonParsingTask(String filePath) {
        this.filePath = filePath;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    @Override
    public List<ProcessComplexJsonData> call() throws Exception {
        ObjectMapper objectMapper = getObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<List<ProcessComplexJsonData>>() {
        });
    }
}

class DataSavingTask implements Runnable {

    @Autowired
    ProcessComplexJsonDataRepository processComplexJsonDataRepository;

    private final List<ProcessComplexJsonData> data;

    public DataSavingTask(List<ProcessComplexJsonData> data) {
        this.data = data;
    }

    @Override
    public void run() {
        processComplexJsonDataRepository.saveAll(data);
    }
}
