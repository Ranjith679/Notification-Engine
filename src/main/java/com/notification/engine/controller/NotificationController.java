package com.notification.engine.controller;

import com.notification.engine.dto.NotificationRequest;
import com.notification.engine.dto.NotificationResponse;
import com.notification.engine.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponse createNotification(
            @Valid @RequestBody NotificationRequest request) {

        return notificationService.createNotification(request);
    }

    @GetMapping("/{id}")
    public NotificationResponse getNotification(@PathVariable Long id) {

        return notificationService.getNotification(id);
    }

}