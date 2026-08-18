package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.event.NotificationEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


/***
 * This class is a notification handler for in-app messages.
 *
 * When a notification event comes in:
 *
 * It decides the channel (IN_APP).
 *
 * It builds the destination topic for the specific user.
 *
 * It sends the event to that topic using Spring’s messaging system.
 *
 * Clients connected via WebSocket and subscribed to that topic will see the notification pop up in real time.
 */
@Component
public class WebSocketNotificationHandler
        implements NotificationChannelHandler {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationHandler(
            SimpMessagingTemplate messagingTemplate) {

        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.IN_APP;
    }

    @Override
    public void send(NotificationEvent event) {

        // Each user gets their own notification destination.
        String destination = "/topic/notifications/" + event.getUserId();

        // Send the notification to all clients subscribed to this destination.
        messagingTemplate.convertAndSend(
                destination,
                event
        );
    }
}