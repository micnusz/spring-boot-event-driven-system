package com.micnusz.eds.service;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.micnusz.eds.dto.user.UserCreatedEvent;
import com.micnusz.eds.dto.user.UserRequest;
import com.micnusz.eds.dto.user.UserResponse;
import com.micnusz.eds.mapper.UserMapper;
import com.micnusz.eds.model.User;
import com.micnusz.eds.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
     private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;


    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User userEntity = userMapper.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(userEntity);

        UserCreatedEvent event = new UserCreatedEvent(saved.getId(), saved.getEmail(), saved.getUsername());
        kafkaTemplate.send("user-created-topic", event);

        return userMapper.toResponse(saved);
    }

    public UserResponse getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toResponse(user);
    }
}
