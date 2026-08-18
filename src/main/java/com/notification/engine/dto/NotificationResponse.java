package com.notification.engine.dto;

import com.notification.engine.entity.Notification;
import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.entity.NotificationStatus;

import java.time.LocalDateTime;

public class NotificationResponse {

    private Long id;
    private String userId;
    private NotificationChannel channel;
    private String title;
    private String message;
    private NotificationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

    public NotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.userId = notification.getUserId();
        this.channel = notification.getChannel();
        this.title = notification.getTitle();
        this.message = notification.getMessage();
        this.status = notification.getStatus();
        this.createdAt = notification.getCreatedAt();
        this.sentAt = notification.getSentAt();
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}