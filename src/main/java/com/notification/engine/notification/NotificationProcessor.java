package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import com.notification.engine.event.NotificationEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationProcessor {

    private final Map<NotificationChannel, NotificationChannelHandler> handlers;

    public NotificationProcessor(List<NotificationChannelHandler> channelHandlers) {

        // Build a lookup map once when the application starts.
        this.handlers = channelHandlers.stream()
                .collect(Collectors.toMap(
                        NotificationChannelHandler::getChannel,
                        Function.identity()
                ));
    }

    public void process(NotificationEvent event) {

        NotificationChannelHandler handler =
                handlers.get(event.getChannel());

        if (handler == null) {
            throw new IllegalArgumentException(
                    "No handler found for channel: "
                            + event.getChannel()
            );
        }

        handler.send(event);
    }
}