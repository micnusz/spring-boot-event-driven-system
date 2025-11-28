package com.micnusz.eds.mapper;

import org.springframework.stereotype.Component;

import com.micnusz.eds.dto.notification.NotificationRequest;
import com.micnusz.eds.dto.notification.NotificationResponse;
import com.micnusz.eds.model.Notification;

@Component
public class NotificationMapper {
    
    public Notification toEntity(NotificationRequest request) {
        return Notification.builder().userId(request.getUserId()).message(request.getMessage()).build();

    }

    public NotificationResponse toResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .message(notification.getMessage())
                .read(notification.isRead())
                .sentAt(notification.getSentAt())
                .build();
    }
}
