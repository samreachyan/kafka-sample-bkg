package com.sakcode.consumer.listener;

import com.sakcode.consumer.service.TelegramNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TelegramNotifListener {
    @Autowired
    private TelegramNotificationService telegramNotificationService;

    @KafkaListener(topics = "push_telegram", groupId = "group-1")
    public void listen(String message) {
        long pTime = System.nanoTime();
        log.info("Received and Trying Push to Telegram: {}", message);
        // TODO send telegram bot
        telegramNotificationService.sendNotification(message);
        long eTime = System.nanoTime();
        log.info("Time taken: {} nano seconds for [{}]", eTime - pTime, message);
    }
}
