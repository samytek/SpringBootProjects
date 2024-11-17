package com.example.SpringBootBatchProcessingJPA.Config;

import com.example.SpringBootBatchProcessingJPA.Entity.Employees;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

public class CustomerProcessor implements ItemProcessor<Employees, Employees> {

    @Override
    public Employees process(Employees employees) throws Exception {
        if (employees.getFirstName().equalsIgnoreCase("Theresa")) {
            System.out.println("Processing stopped: 500 records processed.");
            throw new RuntimeException("Processing stopped: 500 records processed.");
        }
        return employees;
    }
}