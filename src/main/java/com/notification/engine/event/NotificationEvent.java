package com.notification.engine.event;

import com.notification.engine.entity.NotificationChannel;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationEvent {

    private UUID eventId;

    private Long notificationId;

    private String userId;

    private NotificationChannel channel;

    private String recipient;

    private String title;

    private String message;

    private LocalDateTime createdAt;

    public NotificationEvent() {
        // Required for JSON deserialization by Kafka.
    }

    public NotificationEvent(
            Long notificationId,
            String userId,
            NotificationChannel channel,
            String recipient,
            String title,
            String message
    ) {
        this.eventId = UUID.randomUUID();
        this.notificationId = notificationId;
        this.userId = userId;
        this.channel = channel;
        this.recipient = recipient;
        this.title = title;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getEventId() {
        return eventId;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}