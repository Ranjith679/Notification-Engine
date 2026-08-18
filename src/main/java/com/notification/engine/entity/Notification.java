package com.notification.engine.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;

    private String recipient;

    private String title;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

    // JPA requires a no-argument constructor.
    public Notification() {
    }

    // Constructor used when creating a new notification.
    public Notification(
            String userId,
            NotificationChannel channel,
            String recipient,
            String title,
            String message
    ) {
        this.userId = userId;
        this.channel = channel;
        this.recipient = recipient;
        this.title = title;
        this.message = message;
        this.status = NotificationStatus.CREATED;
        this.createdAt = LocalDateTime.now();
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

    public String getRecipient() {
        return recipient;
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

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}