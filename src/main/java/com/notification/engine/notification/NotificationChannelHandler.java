package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.event.NotificationEvent;

public interface NotificationChannelHandler {

    // So if the system asks “Which channel do you handle?” → it replies “IN_APP”.
    NotificationChannel getChannel();

    void send(NotificationEvent event);
}