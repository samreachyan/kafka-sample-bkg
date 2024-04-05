package com.sakcode.producer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String message) {
        log.info("Sending message = {}", message);
        log.info("------------------------------");

        kafkaTemplate.send(topicName, message);
        log.info("Sent successfully!!!");
    }
}
