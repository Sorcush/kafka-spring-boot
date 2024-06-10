package com.example.kafka_producer.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PublisherService {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void publishMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("demo-topic", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message=[{}] due to: {}", message, ex.getMessage());
            }
        });
    }
}
