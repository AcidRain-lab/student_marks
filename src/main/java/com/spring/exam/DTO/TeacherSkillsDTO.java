package com.spring.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherSkillsDTO {
    private Long teacherId;
    private String firstName;
    private String lastName;
    private List<SkillCheckbox> skills;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SkillCheckbox {
        private Long skillId;
        private String skillName;
        private boolean selected; // Чекбокс отмечен, если навык связан с учителем
    }
}

