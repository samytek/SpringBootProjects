package com.example.SpringBootBatchProcessingJPA.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobCompletionNotificationImpl implements JobExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationImpl.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info(":::::::: JobCompletionNotificationImpl Job Started::::::::: "+new Date());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info(":::::::: JobCompletionNotificationImpl Job Completed::::::::: "+new Date());
        }
    }
}
