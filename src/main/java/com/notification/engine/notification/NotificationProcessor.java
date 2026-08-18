package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationProcessor {

    private static final Logger log = LoggerFactory.getLogger(NotificationProcessor.class);

    private final Map<NotificationChannel, NotificationChannelHandler> handlers;
    private final NotificationMetrics notificationMetrics;

    public NotificationProcessor(List<NotificationChannelHandler> channelHandlers,NotificationMetrics notificationMetrics) {

        // Build a lookup map once when the application starts.
        this.handlers = channelHandlers.stream()
                .collect(Collectors.toMap(
                        NotificationChannelHandler::getChannel,
                        Function.identity()
                ));
        this.notificationMetrics = notificationMetrics;
    }

    public void process(NotificationEvent event) {

        log.info(
                "Processing notification | userId={} | channel={}",
                event.getUserId(),
                event.getChannel()
        );

        NotificationChannelHandler handler =
                handlers.get(event.getChannel());

        if (handler == null) {

            log.error(
                    "No handler found | channel={}",
                    event.getChannel()
            );

            throw new IllegalArgumentException(
                    "Unsupported notification channel: "
                            + event.getChannel()
            );
        }

        handler.send(event);

        notificationMetrics.incrementProcessed();

        notificationMetrics.incrementProcessedByChannel(event.getChannel());

        log.info(
                "Notification processed successfully | userId={} | channel={}",
                event.getUserId(),
                event.getChannel()
        );
    }
}