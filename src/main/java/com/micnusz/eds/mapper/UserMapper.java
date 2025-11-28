package com.micnusz.eds.mapper;

import org.springframework.stereotype.Component;

import com.micnusz.eds.dto.user.UserRequest;
import com.micnusz.eds.dto.user.UserResponse;
import com.micnusz.eds.model.User;

@Component
public class UserMapper {
    
    public User toEntity(UserRequest request) {
        return User.builder().username(request.getUsername()).email(request.getEmail()).password(request.getPassword())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail())
                .createdAt(user.getCreatedAt()).build();
    }
}
