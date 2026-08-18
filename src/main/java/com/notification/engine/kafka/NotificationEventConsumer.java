package com.notification.engine.kafka;

import com.notification.engine.event.NotificationEvent;
import com.notification.engine.notification.NotificationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(NotificationEventConsumer.class);

    private final NotificationProcessor notificationProcessor;

    public NotificationEventConsumer(
            NotificationProcessor notificationProcessor) {

        this.notificationProcessor = notificationProcessor;
    }

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(
                    delay = 2000,
                    multiplier = 2.0
            )
    )
    @KafkaListener(
            topics = "notification-events",
            groupId = "notification-engine"
    )
    public void consume(NotificationEvent event) {

        log.info(
                "Kafka event received | userId={} | channel={}",
                event.getUserId(),
                event.getChannel()
        );

        notificationProcessor.process(event);
    }
}