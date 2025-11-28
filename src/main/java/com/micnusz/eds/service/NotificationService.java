package com.micnusz.eds.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.micnusz.eds.dto.notification.NotificationRequest;
import com.micnusz.eds.dto.notification.NotificationResponse;
import com.micnusz.eds.mapper.NotificationMapper;
import com.micnusz.eds.model.Notification;
import com.micnusz.eds.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationResponse createNotification(NotificationRequest request) {
        Notification saved = notificationRepository.save(notificationMapper.toEntity(request));
        return notificationMapper.toResponse(saved);
    }

    public List<NotificationResponse> getNotificationForUser(UUID userId) {
        return notificationRepository.findAllByUserId(userId).stream().map(notificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
