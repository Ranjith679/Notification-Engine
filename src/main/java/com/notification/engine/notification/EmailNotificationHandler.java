package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.event.NotificationEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationHandler implements NotificationChannelHandler {

    private final JavaMailSender mailSender;

    public EmailNotificationHandler(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public void send(NotificationEvent event) {

        // Create the email.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(event.getRecipient());
        message.setSubject(event.getTitle());
        message.setText(event.getMessage());

        // Send it through our configured SMTP server.
        mailSender.send(message);
    }
}