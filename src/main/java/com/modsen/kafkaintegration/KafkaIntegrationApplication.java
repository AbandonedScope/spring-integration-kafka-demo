package com.modsen.kafkaintegration;

import com.modsen.kafkaintegration.kafka.message.SimpleMessage;
import com.modsen.kafkaintegration.service.SendRequestHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class KafkaIntegrationApplication {

    public static void main(String[] args) throws InterruptedException {
        var context = SpringApplication.run(KafkaIntegrationApplication.class, args);
        var handler = context.getBean(SendRequestHandler.class);
        while (true) {
            Thread.sleep(500);
            SimpleMessage message = SimpleMessage.builder()
                    .payload("Hello World!")
                    .sentAt(ZonedDateTime.now())
                    .build();
            handler.handleRequest(message);
        }
    }
}
