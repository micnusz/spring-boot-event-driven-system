package com.micnusz.eds.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micnusz.eds.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    
    boolean existsByEmail(String email);
}
