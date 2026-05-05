package com.bkadioglu.odev2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkadioglu.odev2.entity.RefreshToken;
import com.bkadioglu.odev2.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user); 
}