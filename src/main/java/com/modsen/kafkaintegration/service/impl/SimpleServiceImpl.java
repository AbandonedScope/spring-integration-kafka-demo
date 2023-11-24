package com.modsen.kafkaintegration.service.impl;

import com.modsen.kafkaintegration.kafka.message.SimpleMessage;
import com.modsen.kafkaintegration.service.SimpleService;

public class SimpleServiceImpl implements SimpleService {
    @Override
    public void doSomeLogic(SimpleMessage message) {
        System.out.println("Received message: " + message.payload());
        System.out.println("At : " + message.sentAt());
    }
}
