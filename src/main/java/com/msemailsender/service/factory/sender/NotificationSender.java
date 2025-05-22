package com.msemailsender.service.factory.sender;


import com.msemailsender.model.NotificationCreateRequest;
import com.msemailsender.model.enums.ChannelType;

public interface NotificationSender {

    ChannelType getSupportedChannelType();

    void sendNotification(NotificationCreateRequest notificationCreateRequest);
}
