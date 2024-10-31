package com.example.SpringBootBatchProcessingJPA.Config;

import com.example.SpringBootBatchProcessingJPA.Entity.Employees;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Employees, Employees> {

    @Override
    public Employees process(Employees employees) throws Exception {
        return employees;
    }
}