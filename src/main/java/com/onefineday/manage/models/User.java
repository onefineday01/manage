package com.onefineday.manage.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data // For all getters and setters
@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "password"})})  // Unique constraint if need to make joint index on 2 or more columns
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)  // Makes username non-nullable and unique
    private String username;

    @Column(nullable = false)  // Makes password non-nullable
//    @JsonIgnore  // This will prevent the password from being serialized in the response
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Allow password to be written, but not read
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ADMIN', 'USER') DEFAULT 'USER'")  // Default value for role
    private Role role = Role.USER;  // Sets default value for role in code

    @CreatedDate // Automatically populated when the entity is created
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updated when the entity is modified
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

    public String getRole() {
        return role.toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}