package com.sakcode.producer.rest;

import com.sakcode.producer.utils.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TransactionController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping
    public ResponseEntity<?> process() {
        log.info("Initial!!!!...........");

        for (int i = 0; i < 100; i++) {
//            CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send("Send from Appz1", "topic-1");
//            result.whenComplete((sr, ex) -> log.info("Sent({}): {}", sr.getProducerRecord().key(), sr.getProducerRecord().value()));

            messageProducer.sendMessage("topic-1", "From APPZ1");
            log.info(".... Done index = {} ....", i);
        }
        return ResponseEntity.ok("Done!!");
    }
}
