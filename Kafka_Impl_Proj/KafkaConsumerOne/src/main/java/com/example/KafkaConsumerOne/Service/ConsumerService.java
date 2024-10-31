package com.example.KafkaConsumerOne.Service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.spring.consumer.group.id}")
    public void listen(ConsumerRecord<String, Service> record) {
        System.out.println("Message Received Consumer Service One: " + record.value());
    }
}