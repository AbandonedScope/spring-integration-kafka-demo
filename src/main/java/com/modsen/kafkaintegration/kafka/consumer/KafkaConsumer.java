package com.modsen.kafkaintegration.kafka.consumer;

import com.modsen.kafkaintegration.kafka.message.SimpleMessage;
import com.modsen.kafkaintegration.service.SimpleService;
import com.modsen.kafkaintegration.service.impl.SimpleServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumer {
    @Bean
    public SimpleService simpleService() {
        return new SimpleServiceImpl();
    }

    @Bean
    public IntegrationFlow consumeFromKafka(ConsumerFactory<String, String> consumerFactory) {
        return IntegrationFlow.from(Kafka.messageDrivenChannelAdapter(consumerFactory, "test"))
                .handle(simpleService(), "doSomeLogic")
                .get();
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                JsonDeserializer.TYPE_MAPPINGS, "simpleMessage:" + SimpleMessage.class.getName(),
                ConsumerConfig.GROUP_ID_CONFIG, "test-group"
        );
    }
}
