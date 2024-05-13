package com.sakcode.producer.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/tg")
public class PushTelegramController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/push")
    ResponseEntity<?> getPushTg(@RequestBody String message) {
        log.info("Push Telegram message: {}", message);
        long pTime = System.currentTimeMillis();
        for (int i = 1; i <= 50; ++i) {
            long pTime1 = System.nanoTime();
            kafkaTemplate.send("push_telegram", message + "_" + i);
            long eTime1 = System.nanoTime();
            log.info("Publish a message to client - {} taken {} nano seconds", i, eTime1 - pTime1);
        }
        long endTime = System.currentTimeMillis();
        long diff = endTime - pTime;
        log.info("Push Telegram duration: {} ms", diff);
        log.info("Push End Telegram message: {}", message);
        return ResponseEntity.ok().body("Received Push Telegram message");
    }
}
