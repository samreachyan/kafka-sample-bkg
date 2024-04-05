package com.sakcode.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionListener {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listen(String message) {
        log.info("Received message: {} ", message);
        for (int i = 0; i < 10; i++) {
            try {
                log.info("------- WORKING HERE i = {}", i);
//                Thread.sleep(100);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        log.info("------ Done executed!!!");
    }
}
