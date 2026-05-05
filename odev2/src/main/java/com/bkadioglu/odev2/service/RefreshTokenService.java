package com.bkadioglu.odev2.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkadioglu.odev2.entity.RefreshToken;
import com.bkadioglu.odev2.repository.RefreshTokenRepository;
import com.bkadioglu.odev2.repository.UserRepository;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        
        refreshToken.setUser(userRepository.findByUsername(username).get());
        refreshToken.setToken(UUID.randomUUID().toString()); 
        refreshToken.setExpiryDate(Instant.now().plusMillis(1000 * 60 * 60 * 24));

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
