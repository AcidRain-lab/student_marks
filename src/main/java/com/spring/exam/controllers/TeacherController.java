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

    // Отображение всех оценок
    @GetMapping("/grades")
    public String viewGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "teacher/grades";
    }

    // Отображение формы для создания оценки
    @GetMapping("/grades/new")
    public String showCreateForm(Model model) {
        model.addAttribute("grade", new Grade());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "teacher/grade-form";
    }

    // Сохранение новой оценки
    @PostMapping("/grades")
    public String saveGrade(@ModelAttribute Grade grade, @RequestParam("teacherId") Long teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        grade.setTeacher(teacher);
        gradeService.saveGrade(grade);
        return "redirect:/teacher/grades";
    }

    // Отображение формы для редактирования оценки
    @GetMapping("/grades/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade", grade);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "teacher/grade-form";
    }

    // Обновление оценки
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

    // Удаление оценки
    @GetMapping("/grades/delete/{id}")
    public String deleteGrade(@PathVariable("id") Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/teacher/grades";
    }
}