package com.spring.exam.controllers;

import com.spring.exam.Models.Grade;
import com.spring.exam.Models.Student;
import com.spring.exam.service.GradeService;
import com.spring.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final GradeService gradeService;

    @Autowired
    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    // Отображение списка всех студентов
    @GetMapping("/list")
    public String viewStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/list";
    }

    // Отображение формы для создания студента
    @GetMapping("/new")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/student-form";
    }

    // Сохранение нового студента
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/student/list";
    }

    // Отображение формы для редактирования студента
    @GetMapping("/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/student-form";
    }

    // Обновление студента
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/student/list";
    }

    // Удаление студента
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }

    // Просмотр оценок конкретного студента
    @GetMapping("/grades/{id}")
    public String viewStudentGrades(@PathVariable("id") Long id, Model model) {
        List<Grade> grades = studentService.getGradesByStudent(id);
        model.addAttribute("grades", grades);
        return "student/grades";
    }
}

