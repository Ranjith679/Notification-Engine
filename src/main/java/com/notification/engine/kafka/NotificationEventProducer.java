package com.notification.engine.kafka;

import com.notification.engine.event.NotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NotificationEventProducer {

    private static final String TOPIC = "notification-events";

    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public NotificationEventProducer(
            KafkaTemplate<String, NotificationEvent> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(NotificationEvent event) {

        // userId is used as the Kafka key.
        try {
            // Wait for Kafka to confirm the record was sent.
            kafkaTemplate.send(
                    TOPIC,
                    event.getUserId(),
                    event
            ).get(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to publish notification event to Kafka", e
            );
        }
    }
}