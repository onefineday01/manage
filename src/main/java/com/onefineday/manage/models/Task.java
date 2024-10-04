package com.onefineday.manage.models;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data // For all getters and setters
@Entity
@Table(name = "tasks")  // Unique constraint
public class Task extends Updationentity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('URGENT','HIGH','NORMAL','LOW') DEFAULT 'NORMAL'")  // Default value for role
    private Priority priority;  // Enum for priority

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('OPEN','READY_TO_PICK','IN_PROGRESS','ON_HOLD','DONE') DEFAULT 'OPEN'")  // Default value for role
    private Status status;  // Enum for status

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Assuming User is another entity
}