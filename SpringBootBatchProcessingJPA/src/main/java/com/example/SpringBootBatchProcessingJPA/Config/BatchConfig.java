package com.example.SpringBootBatchProcessingJPA.Config;

import com.example.SpringBootBatchProcessingJPA.Entity.Employees;
import com.example.SpringBootBatchProcessingJPA.Repository.EmployeeRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.io.File;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public FlatFileItemReader<Employees> reader() {
        FlatFileItemReader<Employees> itemReader = new FlatFileItemReader<>();
        File file = new File("C:/Users/Asus/OneDrive/Desktop/employees2.csv");
        itemReader.setResource(new FileSystemResource(file));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Employees> lineMapper() {
        DefaultLineMapper<Employees> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("firstName", "gender", "startDate", "lastLoginTime", "salary", "bonus", "seniorManagement", "team");

        BeanWrapperFieldSetMapper<Employees> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employees.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }

    @Bean
    public RepositoryItemWriter<Employees> writer() {
        RepositoryItemWriter<Employees> writer = new RepositoryItemWriter<>();
        writer.setRepository(employeeRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step steps(JobRepository jobRepository) {
        return new StepBuilder("jobStep", jobRepository).<Employees, Employees>chunk(10, new JpaTransactionManager(entityManagerFactory))
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job jobBean(JobRepository jobRepository, JobCompletionNotificationImpl listener, Step steps) {
        return new JobBuilder("job", jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

}
