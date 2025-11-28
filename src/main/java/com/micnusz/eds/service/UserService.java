package com.micnusz.eds.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.micnusz.eds.dto.user.UserRequest;
import com.micnusz.eds.dto.user.UserResponse;
import com.micnusz.eds.mapper.UserMapper;
import com.micnusz.eds.model.User;
import com.micnusz.eds.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User saved = userRepository.save(userMapper.toEntity(request));
        return userMapper.toResponse(saved);
    }

    public UserResponse getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toResponse(user);
    }
}
