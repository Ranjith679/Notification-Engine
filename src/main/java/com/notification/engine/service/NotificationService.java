package com.notification.engine.service;

import com.notification.engine.dto.NotificationRequest;
import com.notification.engine.dto.NotificationResponse;
import com.notification.engine.entity.Notification;
import com.notification.engine.event.NotificationEvent;
import com.notification.engine.kafka.NotificationEventProducer;
import com.notification.engine.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationEventProducer eventProducer;

    // Spring injects NotificationRepository and NotificationEventProducer through the constructor.
    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationEventProducer eventProducer) {

        this.notificationRepository = notificationRepository;
        this.eventProducer = eventProducer;
    }

    public NotificationResponse createNotification(NotificationRequest request) {

        // Convert the API request into our database entity.
        Notification notification = new Notification(
                request.getUserId(),
                request.getChannel(),
                request.getRecipient(),
                request.getTitle(),
                request.getMessage()
        );

        // Save the notification in H2.
        Notification savedNotification = notificationRepository.save(notification);

        // Create an event representing the notification.
        NotificationEvent event = new NotificationEvent(
                savedNotification.getId(),
                savedNotification.getUserId(),
                savedNotification.getChannel(),
                savedNotification.getRecipient(),
                savedNotification.getTitle(),
                savedNotification.getMessage()
        );

        // Publish the event to Kafka.
        eventProducer.publish(event);

        // Convert the saved entity into the API response.
        return new NotificationResponse(savedNotification);
    }

    public NotificationResponse getNotification(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Notification not found: " + id
                ));

        return new NotificationResponse(notification);
    }
}