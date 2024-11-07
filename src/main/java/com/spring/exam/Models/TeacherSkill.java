package com.spring.exam.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("teacher_skills")
public class TeacherSkill {

    @Id
    private Long id;  // Идентификатор связи
    private Long teacherId;
    private Long skillId;
}
