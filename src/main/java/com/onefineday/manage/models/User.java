package com.onefineday.manage.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data // For all getters and setters
@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "password"})})  // Unique constraint if need to make joint index on 2 or more columns
@Table(name = "users")
public class User extends Updationentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)  // Makes username non-nullable and unique
    private String username;

    @Column(nullable = false)  // Makes password non-nullable
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('ADMIN', 'USER') DEFAULT 'USER'")  // Default value for role
    private Role role = Role.USER;  // Sets default value for role in code


    public String getRole() {
        return role.toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}