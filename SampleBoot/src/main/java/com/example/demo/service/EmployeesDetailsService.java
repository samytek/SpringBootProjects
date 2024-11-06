package com.example.demo.service;

import com.example.demo.entity.EmployeesDetails;
import com.example.demo.repository.EmployeesDetailsRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployeesDetailsService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EmployeesDetailsRepository employeesDetailsRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final AtomicInteger saveCount = new AtomicInteger(0);

    //Spring’s @Transactional annotation and transaction management are bound to a single thread. When a transaction begins, it’s tied to the current thread where it started.
    //Each thread has its own context, so when tasks are run in multiple threads, they do not share the same transaction context.
    //In multithreading, if one thread rolls back, the changes made by other threads might not roll back, breaking the atomicity of the transaction.

    public List<EmployeesDetails> readEmployeesFromCsv(File csvFile) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withSkipFirstDataRow(true);
        MappingIterator<EmployeesDetails> iterator = csvMapper
                .readerFor(EmployeesDetails.class)
                .with(schema)
                .readValues(csvFile);
        return iterator.readAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void processCsvFile(File csvFile) throws IOException {
        List<EmployeesDetails> employees = readEmployeesFromCsv(csvFile);
        for (EmployeesDetails employee : employees) {
            executorService.submit(() -> {
                TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
                transactionTemplate.executeWithoutResult(status -> {
                    employeesDetailsRepository.save(employee);
                    int currentCount = saveCount.incrementAndGet();
                    if (currentCount >= 50) try {
                        throw new Exception();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

//    @Transactional
//    public void processCsvFile(File csvFile) throws IOException {
//        List<EmployeesDetails> employees = readEmployeesFromCsv(csvFile);
//        CountDownLatch latch = new CountDownLatch(employees.size()); // Count down latch to track completion
//        long startTime = System.currentTimeMillis(); // Start time
//        for (EmployeesDetails employee : employees) {
//            executorService.submit(() -> {
//                try {
//                    saveEmployee(employee);
//                    int currentCount = saveCount.incrementAndGet();
//                    if (currentCount == 200) {
//                        throw new RuntimeException("Reached 200 employees; rolling back transaction.");
//                    }
//                } finally {
//                    latch.countDown(); // Decrease count when done
//                }
//            });
//        }
//        try {
//            latch.await(); // Wait for all tasks to complete
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Restore interrupted status
//        } finally {
//            executorService.shutdown(); // Shutdown the executor
//        }
//        long endTime = System.currentTimeMillis(); // End time
//        long timeTaken = endTime - startTime; // Calculate time taken
//        System.out.println("Time taken to save all employees: " + timeTaken + " ms");
//    }

    @Transactional
    public void saveEmployees() {
        List<EmployeesDetails> employees = createDummyEmployees();
        for (EmployeesDetails employee : employees) {
            employeesDetailsRepository.save(employee);
            if (employee.getId() == 60) {
                throw new RuntimeException("Simulated error after saving 5 records");
            }
        }
    }

    private List<EmployeesDetails> createDummyEmployees() {
        List<EmployeesDetails> employees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            EmployeesDetails employee = new EmployeesDetails();
            employee.setFirstName("Sameer");
            employee.setGender("Male");
            employee.setStartDate(new Date());
            employee.setLastLoginTime(new Date());
            employee.setSalary(500.00);
            employee.setBonus(500.00);
            employee.setSeniorManagement(true);
            employee.setTeam("IT");
            employees.add(employee);
        }
        return employees;
    }
}
