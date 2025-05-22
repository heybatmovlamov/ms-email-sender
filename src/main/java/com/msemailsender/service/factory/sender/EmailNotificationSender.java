package com.msemailsender.service.factory.sender;

import com.msemailsender.model.NotificationCreateRequest;
import com.msemailsender.model.enums.ChannelType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationSender implements NotificationSender {

    private final JavaMailSender mailSender;

    @Value("$(spring.mail.username)")
    private String fromMailId;

    @Override
    public ChannelType getSupportedChannelType() {
        return ChannelType.EMAIL;
    }

    @Override
    public void sendNotification(NotificationCreateRequest event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail());
        message.setSubject("OTP CONFIRMATION");
        message.setText(String.valueOf(event.getOtp()));
        message.setFrom(fromMailId);

        mailSender.send(message);
    }
}
