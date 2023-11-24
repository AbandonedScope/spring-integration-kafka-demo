package com.modsen.kafkaintegration.kafka.message;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SimpleMessage(
        String payload,
        ZonedDateTime sentAt
) {
}
