package com.notification.engine.notification.push;

import com.notification.engine.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LocalPushNotificationProvider
        implements PushNotificationProvider {

    private static final Logger log =
            LoggerFactory.getLogger(LocalPushNotificationProvider.class);

    @Override
    public void send(NotificationEvent event) {

        // Simulates sending a push notification.
        log.info(
                "PUSH notification sent to user {}: {} - {}",
                event.getUserId(),
                event.getTitle(),
                event.getMessage()
        );
    }
}