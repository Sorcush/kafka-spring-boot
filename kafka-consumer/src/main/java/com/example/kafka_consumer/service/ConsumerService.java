package com.example.kafka_consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "demo-topic", groupId = "demo-group")
    public void consumeMessagePartition0(String message) {
        log.info("The message: {} is consumed from partition 0", message);
    }

    @KafkaListener(topics = "demo-topic", groupId = "demo-group")
    public void consumeMessagePartition1(String message) {
        log.info("The message: {} is consumed from partition 1", message);
    }

    @KafkaListener(topics = "demo-topic", groupId = "demo-group")
    public void consumeMessagePartition2(String message) {
        log.info("The message: {} is consumed from partition 2", message);
    }

}
