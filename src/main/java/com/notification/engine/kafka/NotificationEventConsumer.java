package com.notification.engine.kafka;

import com.notification.engine.event.NotificationEvent;
import com.notification.engine.notification.NotificationProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventConsumer {

    private final NotificationProcessor notificationProcessor;

    public NotificationEventConsumer(
            NotificationProcessor notificationProcessor) {
        this.notificationProcessor = notificationProcessor;
    }

    @KafkaListener(topics = "notification-events")
    public void consume(NotificationEvent event) {

        notificationProcessor.process(event);
    }
}