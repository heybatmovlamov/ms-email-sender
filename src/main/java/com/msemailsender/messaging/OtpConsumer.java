package com.msemailsender.messaging;

import com.msemailsender.model.NotificationCreateRequest;
import com.msemailsender.service.NotificationRabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpConsumer {

    private final NotificationRabbitService notificationService;


    @RabbitListener(queues = "otp.queue")
    public void consumeOtp(NotificationCreateRequest event) {
        log.info("Consuming OTP request: {}", event);
        notificationService.createNotification(event);
    }
}
