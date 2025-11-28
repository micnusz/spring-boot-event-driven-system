package com.micnusz.eds.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "notifications")
@Data
@Builder
@AllArgsConstructor
public class Notification {
    
    @Id
    @GeneratedValue(generator="uuid2")
    @Column(columnDefinition="Binary(16)")
    private UUID id;

    @Column(nullable=false)
    private UUID userId;

    @Column(nullable=false)
    private String message;

    @Column(nullable = false)
    private boolean read = false;

    @Column(nullable = false, updatable = false)
    private Instant sentAt = Instant.now();
}
