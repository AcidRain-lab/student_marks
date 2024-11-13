package com.spring.exam.controllers;

import com.spring.exam.Models.Grade;
import com.spring.exam.Models.Teacher;
import com.spring.exam.service.GradeService;
import com.spring.exam.service.StudentService;
import com.spring.exam.service.SubjectService;
import com.spring.exam.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(GradeService gradeService, StudentService studentService,
                             SubjectService subjectService, TeacherService teacherService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    // --- Методы для управления оценками (старые) ---

    @GetMapping("/grades")
    public String viewGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "teacher/grades";
    }

    @GetMapping("/grades/new")
    public String showCreateForm(Model model) {
        model.addAttribute("grade", new Grade());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "teacher/grade-form";
    }

    @PostMapping("/grades")
    public String saveGrade(@ModelAttribute Grade grade, @RequestParam("teacherId") Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        grade.setTeacher(teacher);
        gradeService.saveGrade(grade);
        return "redirect:/teacher/grades";
    }

    @GetMapping("/grades/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade", grade);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "teacher/grade-form";
    }

    @PostMapping("/grades/{id}")
    public String updateGrade(@PathVariable("id") Long id, @ModelAttribute Grade grade) {
        Grade existingGrade = gradeService.getGradeById(id);
        if (existingGrade != null) {
            existingGrade.setScore(grade.getScore());
            existingGrade.setComment(grade.getComment());
            existingGrade.setDate(grade.getDate());
            existingGrade.setStudent(grade.getStudent());
            existingGrade.setSubject(grade.getSubject());
            gradeService.saveGrade(existingGrade);
        }
        return "redirect:/teacher/grades";
    }

    @GetMapping("/grades/delete/{id}")
    public String deleteGrade(@PathVariable("id") Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/teacher/grades";
    }

    // --- Методы для управления учителями (новые) ---

    @GetMapping("/list")
    public String viewTeachers(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/list";
    }

    @GetMapping("/new")
    public String showCreateTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/teacher-form";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/teacher/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditTeacherForm(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-form";
    }

    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") Long id, @ModelAttribute Teacher teacher) {
        Teacher existingTeacher = teacherService.getTeacherById(id);
        if (existingTeacher != null) {
            existingTeacher.setFirstName(teacher.getFirstName());
            existingTeacher.setLastName(teacher.getLastName());
            teacherService.saveTeacher(existingTeacher);
        }
        return "redirect:/teacher/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teacher/list";
    }
}
