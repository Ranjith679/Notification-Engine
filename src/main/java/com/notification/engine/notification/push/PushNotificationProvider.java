package com.notification.engine.notification.push;

import com.notification.engine.event.NotificationEvent;


/***
 * Why seperate Interface , now we created LocalPushNotificationProvider , later we may use real services like Firebase Cloud Messaging
 */
public interface PushNotificationProvider {

    void send(NotificationEvent event);
}