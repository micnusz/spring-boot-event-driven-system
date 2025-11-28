package com.micnusz.eds.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micnusz.eds.dto.notification.NotificationRequest;
import com.micnusz.eds.dto.notification.NotificationResponse;
import com.micnusz.eds.service.NotificationService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    
    private final NotificationService notificationService;


    @PostMapping()
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.createNotification(request);
        return ResponseEntity
                .created(URI.create("/notifications/" + response.getId()))
                .body(response);
    }
    
    @GetMapping("{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationForUser(@PathVariable UUID userId) {
        List<NotificationResponse> response = notificationService.getNotificationForUser(userId);
        return ResponseEntity.ok(response);
    }
    
    
}
