package com.micnusz.eds.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micnusz.eds.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
     List<Notification> findAllByUserId(UUID userId);
}
