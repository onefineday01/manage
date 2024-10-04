package com.onefineday.manage.models;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data // For all getters and setters
@Entity
//@Table(name = "tasks") // To name the mysql table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;  // Enum for priority

    @Enumerated(EnumType.STRING)
    private Status status;  // Enum for status

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Assuming User is another entity
}