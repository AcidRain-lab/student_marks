package com.spring.exam.Controllers;

import com.spring.exam.DTO.TeacherSkillsDTO;
import com.spring.exam.Models.Skill;
import com.spring.exam.Service.SkillService;
import com.spring.exam.Service.TeacherService;
import com.spring.exam.Service.TeacherSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/teachers/{teacherId}/skills")
public class TeacherSkillController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private TeacherSkillService teacherSkillService;

    @GetMapping
    public Mono<String> getTeacherSkills(@PathVariable Long teacherId, Model model) {
        return teacherService.getTeacherById(teacherId)
                .zipWith(skillService.getAllSkills().collectList())
                .flatMap(tuple -> {
                    var teacher = tuple.getT1();
                    var allSkills = tuple.getT2();

                    return teacherSkillService.getSkillsByTeacherId(teacherId)
                            .collectList()
                            .map(teacherSkills -> {
                                List<TeacherSkillsDTO.SkillCheckbox> skillCheckboxes = allSkills.stream()
                                        .map(skill -> {
                                            boolean isSelected = teacherSkills.stream()
                                                    .anyMatch(ts -> ts.getSkillId().equals(skill.getSkillId()));
                                            return new TeacherSkillsDTO.SkillCheckbox(skill.getSkillId(), skill.getSkillName(), isSelected);
                                        })
                                        .collect(Collectors.toList());

                                TeacherSkillsDTO dto = new TeacherSkillsDTO();
                                dto.setTeacherId(teacherId);
                                dto.setFirstName(teacher.getFirstName());
                                dto.setLastName(teacher.getLastName());
                                dto.setSkills(skillCheckboxes);

                                model.addAttribute("teacherSkillsDTO", dto);
                                return "teachers/skills";
                            });
                });
    }

    /*
    @PostMapping
    public Mono<String> updateTeacherSkills(@PathVariable Long teacherId, @ModelAttribute TeacherSkillsDTO teacherSkillsDTO) {
        return teacherSkillService.getSkillsByTeacherId(teacherId)
                .flatMap(teacherSkill -> teacherSkillService.removeSkillFromTeacher(teacherId, teacherSkill.getSkillId()))
                .thenMany(Flux.fromIterable(teacherSkillsDTO.getSkills())) // Используем fromIterable для списка
                .flatMap(skillCheckbox -> {
                    if (skillCheckbox.isSelected()) {
                        return teacherSkillService.addSkillToTeacher(teacherId, skillCheckbox.getSkillId());
                    } else {
                        return Mono.empty();
                    }
                })
                .then(Mono.just("redirect:/teachers"));
    }

     */
}
