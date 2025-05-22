package com.msemailsender.service;

import com.msemailsender.model.NotificationCreateRequest;
import com.msemailsender.service.factory.SenderFactory;
import com.msemailsender.service.factory.sender.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationRabbitService {

    private final SenderFactory senderFactory;
//otp
//
    @Transactional
    public void createNotification(final NotificationCreateRequest event) {
        validateRequest(event);
        final NotificationSender notificationSender = senderFactory.getSender(event.getChannelType());
        notificationSender.sendNotification(event);
    }

    public void validateRequest(final NotificationCreateRequest event) {
        switch (event.getChannelType()) {
            case EMAIL -> {
                if (event.getEmail() == null) {
                    throw new IllegalArgumentException("Email cannot be null for EMAIL channel");
                }
            }
            case TELEGRAM -> {
                if (event.getTelegramUsername() == null) {
                    throw new IllegalArgumentException("Telegram username cannot be null for TELEGRAM channel");
                }
            }
            default ->  throw new IllegalArgumentException("Unsupported channel type: " + event.getChannelType());
        }
    }
}
