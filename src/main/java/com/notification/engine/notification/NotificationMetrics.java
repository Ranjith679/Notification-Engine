package com.notification.engine.notification;

import com.notification.engine.entity.NotificationChannel;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class NotificationMetrics {

    private final MeterRegistry meterRegistry;

    private final Counter processedCounter;
    private final Counter failedCounter;

    public NotificationMetrics(MeterRegistry meterRegistry) {

        this.meterRegistry = meterRegistry;

        processedCounter = Counter.builder(
                        "notifications.processed"
                )
                .description(
                        "Number of successfully processed notifications"
                )
                .register(meterRegistry);

        failedCounter = Counter.builder(
                        "notifications.failed"
                )
                .description(
                        "Number of failed notifications"
                )
                .register(meterRegistry);
    }

    public void incrementProcessed() {
        processedCounter.increment();
    }

    public void incrementFailed() {
        failedCounter.increment();
    }

    public void incrementProcessedByChannel(
            NotificationChannel channel) {

        Counter.builder(
                        "notifications.processed.channel"
                )
                .tag("channel", channel.name())
                .description(
                        "Notifications processed by channel"
                )
                .register(meterRegistry)
                .increment();
    }
}