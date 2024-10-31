package com.example.SpringBootBatchProcessing.confiig;

import com.example.SpringBootBatchProcessing.model.Employees;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobBean(JobRepository jobRepository, JobCompletionNotificationImpl listener, Step steps) {
        return new JobBuilder("job", jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }

    @Bean
    public Step steps(JobRepository jobRepository, DataSourceTransactionManager transactionManager, ItemReader<Employees> reader, ItemProcessor<Employees, Employees> processor, ItemWriter<Employees> writer) {
        return new StepBuilder("jobStep", jobRepository)
                .<Employees, Employees>chunk(50, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

    @Bean
    public FlatFileItemReader<Employees> reader() {
        File file = new File("C:\\Users\\Asus\\OneDrive\\Desktop\\employees1.csv");
        Resource resource = new FileSystemResource(file);
        return new FlatFileItemReaderBuilder<Employees>()
                .name("itemReader")
                .resource(resource)
                .delimited()
                .names("firstName", "gender", "startDate", "lastLoginTime", "salary", "bonus", "seniorManagement", "team")
                .targetType(Employees.class)
                .build();
    }

    @Bean
    public ItemProcessor<Employees, Employees> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<Employees> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employees>()
                .sql("INSERT INTO employees(first_Name, gender, start_Date, last_Login_Time, salary, bonus, senior_Management, team) VALUES(:firstName, :gender, :startDate, :lastLoginTime, :salary, :bonus, :seniorManagement, :team)")
                .dataSource(dataSource)
                .beanMapped()
                .build();

    }
}
