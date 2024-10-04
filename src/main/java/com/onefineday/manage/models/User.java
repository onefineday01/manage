package com.onefineday.manage.models;

import jakarta.persistence.*;
import lombok.Data;

@Data // For all getters and setters
@Entity
//@Table(name = "users") // To name the mysql table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}