package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.event.NotificationEvent;
import com.notification.engine.notification.push.PushNotificationProvider;
import org.springframework.stereotype.Component;


/***
 * PUSH
 *  ↓
 * Handler
 *  ↓
 * Provider abstraction
 *  ↓
 * Local provider(this)
 */
@Component
public class PushNotificationHandler
        implements NotificationChannelHandler {

    private final PushNotificationProvider pushProvider;

    public PushNotificationHandler(
            PushNotificationProvider pushProvider) {

        this.pushProvider = pushProvider;
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.PUSH;
    }

    @Override
    public void send(NotificationEvent event) {

//        if ("FAIL".equals(event.getMessage())) {
//
//            throw new RuntimeException(
//                    "Simulated push notification failure"
//            );
//        }
        // Delegate the actual push delivery to the provider.
        pushProvider.send(event);
    }
}