package com.micnusz.eds.Event;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class UserRegistredEvent {
    
    private UUID userId;
    private String email;
    private Instant registredAt;
}
