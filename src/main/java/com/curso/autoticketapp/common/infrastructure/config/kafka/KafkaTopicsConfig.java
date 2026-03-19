package com.curso.autoticketapp.common.infrastructure.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    @Value("${app.kafka.topics.ticket.classification.request}")
    private String ticketRequest;

    @Value("${app.kafka.topics.ticket.classification.response}")
    private String ticketResponse;

    @Bean
    public NewTopic ticketRequestedTopic() {
        return TopicBuilder.name(ticketRequest)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic ticketCompletedTopic() {
        return TopicBuilder.name(ticketResponse)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
