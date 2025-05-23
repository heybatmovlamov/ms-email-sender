package com.msemailsender.service.factory.sender;

import com.msemailsender.config.TwilioConfigProperties;
import com.msemailsender.model.NotificationCreateRequest;
import com.msemailsender.model.enums.ChannelType;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SmsNotificationSender implements NotificationSender {

    private final TwilioConfigProperties properties;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(properties.getAccountSid(), properties.getAuthToken());
    }

    @Override
    public ChannelType getSupportedChannelType() {
        return ChannelType.SMS;
    }

    @Override
    public void sendNotification(NotificationCreateRequest notificationCreateRequest) {
        String phoneNumber = notificationCreateRequest.getPhoneNumber();
        String otp = notificationCreateRequest.getOtp();

        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(properties.getFromNumber()),
                "Sizin OTP kodunuz: " + otp
        ).create();

        System.out.println("Mesaj göndərildi. SID: " + message.getSid());
    }
}
