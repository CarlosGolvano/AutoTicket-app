package com.curso.autoticketapp.common.infrastructure.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    @Value("${app.kafka.topics.ticket.classification.requested}")
    private String ticketRequested;

    @Value("${app.kafka.topics.ticket.classification.completed}")
    private String ticketCompleted;

    @Value("${app.kafka.topics.ticket.classification.processing}")
    private String ticketProcessing;

    @Value("${app.kafka.topics.ticket.classification.failed}")
    private String ticketFailed;

    @Bean
    public NewTopic ticketRequestedTopic() {
        return TopicBuilder.name(ticketRequested)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic ticketCompletedTopic() {
        return TopicBuilder.name(ticketCompleted)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic ticketProcessingTopic() {
        return TopicBuilder.name(ticketProcessing)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic ticketFailedTopic() {
        return TopicBuilder.name(ticketFailed)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
