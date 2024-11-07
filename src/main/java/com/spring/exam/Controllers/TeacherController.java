package com.spring.exam.Controllers;

import com.spring.exam.Models.Teacher;
import com.spring.exam.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String getAllTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teachers/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/create";
    }

    @PostMapping("/create")
    public Mono<String> createTeacher(@ModelAttribute Teacher teacher) {
        return teacherService.saveTeacher(teacher)
                .then(Mono.just("redirect:/teachers"));
    }

    @GetMapping("/edit/{id}")
    public Mono<String> showEditForm(@PathVariable Long id, Model model) {
        return teacherService.getTeacherById(id)
                .map(teacher -> {
                    model.addAttribute("teacher", teacher);
                    return "teachers/edit";
                })
                .defaultIfEmpty("redirect:/teachers");
    }

    @PostMapping("/edit/{id}")
    public Mono<String> updateTeacher(@PathVariable Long id, @ModelAttribute Teacher teacher) {
        return teacherService.updateTeacher(id, teacher)
                .then(Mono.just("redirect:/teachers"));
    }

    @GetMapping("/delete/{id}")
    public Mono<String> deleteTeacher(@PathVariable Long id) {
        return teacherService.deleteTeacher(id)
                .then(Mono.just("redirect:/teachers"));
    }
}
