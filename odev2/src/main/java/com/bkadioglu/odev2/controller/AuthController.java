package com.bkadioglu.odev2.controller;

// import com.bkadioglu.odev2.auth.TokenManager;
// import com.bkadioglu.odev2.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkadioglu.odev2.entity.RefreshToken;
import com.bkadioglu.odev2.entity.User;
import com.bkadioglu.odev2.repository.UserRepository;
// import com.bkadioglu.odev2.repository.UserRepository;
import com.bkadioglu.odev2.request.LoginRequest;
import com.bkadioglu.odev2.request.RefreshTokenRequest;
import com.bkadioglu.odev2.response.JwtResponse;
import com.bkadioglu.odev2.service.JwtService;
import com.bkadioglu.odev2.service.RefreshTokenService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        Authentication auth = authenticationManager.authenticate(authToken);

        if (auth.isAuthenticated()) {
            String accessToken = jwtService.generateToken(loginRequest.getUsername());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());

            return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken.getToken()));
        } else {
            throw new RuntimeException("Geçersiz giriş isteği!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Kullanıcı başarıyla kaydedildi!");
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String newAccessToken = jwtService.generateToken(user.getUsername());
                    return ResponseEntity.ok(new JwtResponse(newAccessToken, request.getRefreshToken()));
                })
                .orElseThrow(() -> new RuntimeException("Refresh Token geçersiz!"));
    }
}