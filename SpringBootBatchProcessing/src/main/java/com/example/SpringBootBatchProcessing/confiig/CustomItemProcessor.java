package com.example.SpringBootBatchProcessing.confiig;

import com.example.SpringBootBatchProcessing.model.Employees;
import lombok.NonNull;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Employees, Employees> {

    @Override
    public Employees process(Employees employees) throws Exception {
        return employees;
    }
}
