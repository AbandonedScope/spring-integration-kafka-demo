package com.modsen.kafkaintegration.kafka;

import com.modsen.kafkaintegration.kafka.message.SimpleMessage;
import com.modsen.kafkaintegration.service.SendRequestHandler;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "sendToKafkaChannel")
public interface KafkaChannelGateway extends SendRequestHandler {
    @Override
    @Gateway(requestChannel = "sendToKafkaChannel")
    void handleRequest(SimpleMessage request);
}
