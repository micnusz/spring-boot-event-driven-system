package com.micnusz.eds.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.micnusz.eds.dto.notification.NotificationRequest;
import com.micnusz.eds.dto.user.UserCreatedEvent;
import com.micnusz.eds.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationListener {
    

    private final NotificationService notificationService;

    @KafkaListener(topics = "user-created-topic", groupId = "notification_group")
    public void handleUserCreated(UserCreatedEvent event) {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(event.getUserId());
        request.setMessage("Welcome " + event.getUsername() + "!");
        notificationService.createNotification(request);
    }
}
