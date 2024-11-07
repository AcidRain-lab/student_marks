package com.spring.exam.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("teachers")
public class Teacher {

    @Id
    private Long teacherId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private String email;
}