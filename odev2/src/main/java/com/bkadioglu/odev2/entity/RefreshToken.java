package com.bkadioglu.odev2.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @OneToOne 
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void setToken(String token) { this.token = token; }
    public String getToken() { return token; }

    public void setExpiryDate(Instant expiryDate) { this.expiryDate = expiryDate; }
    public Instant getExpiryDate() { return expiryDate; }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }
    
    public Long getId() { return id; }
}