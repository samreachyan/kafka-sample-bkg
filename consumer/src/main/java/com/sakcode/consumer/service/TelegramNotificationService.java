package com.sakcode.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TelegramNotificationService {
    private static final String TELEGRAM_API_BASE_URL = "https://api.telegram.org/bot";
    private static final String BOT_TOKEN = "6821421309:xxxxxxxxxxxxxx";
    private static final String CHAT_ID = "xxxxxx";

    @Async
    public CompletableFuture<Void> sendNotification(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String url = TELEGRAM_API_BASE_URL + BOT_TOKEN + "/sendMessage?chat_id=" + CHAT_ID + "&text=" + message;
        try {
            restTemplate.postForObject(url, null, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any potential exceptions here
        }
        return CompletableFuture.completedFuture(null);
    }
}
