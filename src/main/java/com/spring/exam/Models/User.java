package com.spring.exam.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Поля для хранения teacher_id и student_id без связей
    @Column(name = "teacher_id", nullable = true)
    private Long teacherId;

    @Column(name = "student_id", nullable = true)
    private Long studentId;
}
