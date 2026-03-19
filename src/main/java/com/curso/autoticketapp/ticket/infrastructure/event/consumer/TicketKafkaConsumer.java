package com.curso.autoticketapp.ticket.infrastructure.event.consumer;

import com.curso.autoticketapp.common.infrastructure.event.consumer.EventSpecificConsumer;
import com.curso.autoticketapp.common.infrastructure.event.utils.MessagingUtil;
import com.curso.autoticketapp.ticket.domain.exceptions.TicketNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TicketKafkaConsumer {

    private final Map<String, EventSpecificConsumer<SpecificRecord>> eventSpecificConsumerMap;
    private final MessagingUtil messagingUtil;

    public TicketKafkaConsumer(
            List<EventSpecificConsumer> eventSpecificConsumerMap,
            MessagingUtil messagingUtil
    ) {
        this.eventSpecificConsumerMap = eventSpecificConsumerMap
                .stream()
                .collect(Collectors.toMap(EventSpecificConsumer::getSchema, consumer -> consumer));
        this.messagingUtil = messagingUtil;
    }

//    @RetryableTopic(
//            attempts = "4",
//            exclude = {NullPointerException.class, TicketNotFoundException.class},
//            backOff = @BackOff(delay = 3000, multiplier = 1.5, maxDelay = 15000)
//    )
    @KafkaListener(
            topics = "${app.kafka.topics.ticket.classification.response}",
            groupId = "${app.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void accept(Message<GenericRecord> genericRecordMessage) {
        log.info("Generic Message on ticket response listener: {}", genericRecordMessage);
        SpecificRecord specificRecord;

        try {
            specificRecord = messagingUtil.getSpecificRecord(genericRecordMessage.getPayload());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String schemaFullName = specificRecord.getClass().getTypeName();

        EventSpecificConsumer<SpecificRecord> specificConsumer = eventSpecificConsumerMap.get(schemaFullName);

        if (specificConsumer != null) {
            Message<SpecificRecord> specificRecordMessage = messagingUtil.buildMessage(
                    (SpecificRecord) genericRecordMessage.getPayload(), genericRecordMessage.getHeaders()
            );

            specificConsumer.accept(specificRecordMessage);
        } else {
            log.warn("No consumer found for schema: {}", schemaFullName);
        }
    }

    @DltHandler
    public void listenDLT(ConsumerRecord<String, GenericRecord> genericRecord) {}
}
