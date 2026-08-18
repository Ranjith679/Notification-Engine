package com.notification.engine.kafka;

import com.notification.engine.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationDltConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(NotificationDltConsumer.class);

    @KafkaListener(
            topics = "notification-events-dlt",
            groupId = "notification-dlt-handler"
    )
    public void consume(NotificationEvent event) {

        // This event has already failed all retry attempts.
        log.error(
                "Notification permanently failed. " +
                        "Moved to DLT: {}",
                event
        );
    }
}