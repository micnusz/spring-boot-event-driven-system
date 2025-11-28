package com.micnusz.eds.Model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class UserModel {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable=false, unique=true)
    private String email;

    private Instant createdAt = Instant.now();

}
