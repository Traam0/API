package com.isla.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    @Column(nullable = true)
    private LocalDateTime email_verified;
    private LocalDateTime createdAt;
    @Column(nullable = true)
    private LocalDateTime updatedAt;
    private String version;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private java.util.List<Post> posts;

    public User(Long id,
            String username,
            String email,
            String password,
            LocalDateTime email_verified,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String version,
            Role role,
            Privacy privacy) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.email_verified = email_verified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
        this.role = role;
        this.privacy = privacy;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(LocalDateTime email_verified) {
        this.email_verified = email_verified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

}