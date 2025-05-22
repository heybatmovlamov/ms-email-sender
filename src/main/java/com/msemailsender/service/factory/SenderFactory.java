package com.msemailsender.service.factory;

import com.msemailsender.model.enums.ChannelType;
import com.msemailsender.service.factory.sender.NotificationSender;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class SenderFactory {

    private final Map<ChannelType, NotificationSender> sendersMap;

    @Autowired
    private SenderFactory(List<NotificationSender> notificationSenders) {
        sendersMap = notificationSenders.stream()
                .collect(Collectors.toUnmodifiableMap(
                        NotificationSender::getSupportedChannelType,
                        Function.identity()));
    }

    public NotificationSender getSender(ChannelType channelType) {
        return Optional.ofNullable(sendersMap.get(channelType))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported channel type: " + channelType));
    }
}
