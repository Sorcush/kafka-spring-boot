package com.example.kafka_producer.controller;

import com.example.kafka_producer.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class EventController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            publisherService.publishMessage(message);
            return ResponseEntity.ok("message published");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/publish-bulk/{message}")
    public ResponseEntity<?> publishBulk(@PathVariable String message) {
        try {
            for (int i = 0; i < 10000; i++) {
                publisherService.publishMessage(message + i);
            }

            return ResponseEntity.ok("message published");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
