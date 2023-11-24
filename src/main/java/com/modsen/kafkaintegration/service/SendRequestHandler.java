package com.modsen.kafkaintegration.service;

import com.modsen.kafkaintegration.kafka.message.SimpleMessage;

public interface SendRequestHandler {
    void handleRequest(SimpleMessage payload);
}
