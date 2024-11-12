package com.spring.exam.controllers;

import com.spring.exam.Models.Student;
import com.spring.exam.service.GradeService;
import com.spring.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final GradeService gradeService;
    private final StudentService studentService;

    @Autowired
    public StudentController(GradeService gradeService, StudentService studentService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
    }
    // Просмотр оценок студента
    @GetMapping("/grades/{studentId}")
    public String viewGrades(@PathVariable("studentId") Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            model.addAttribute("grades", student.getGrades());
        }
        return "student/grades";
    }
}