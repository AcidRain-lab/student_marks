package com.spring.exam.service;

import com.spring.exam.Models.Teacher;
import com.spring.exam.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.spring.exam.Models.Grade;
import com.spring.exam.Models.Teacher;
import com.spring.exam.repository.GradeRepository;
import com.spring.exam.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    // --- Методы для работы с учителями ---

    // Получение всех учителей
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Получение учителя по ID
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    // Сохранение нового учителя
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Обновление существующего учителя
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);
        if (existingTeacher != null) {
            existingTeacher.setFirstName(teacher.getFirstName());
            existingTeacher.setLastName(teacher.getLastName());
            return teacherRepository.save(existingTeacher);
        }
        return null;
    }

    // Удаление учителя
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    // --- Методы для работы с оценками (старые) ---

    // Получение всех оценок учителя
    public List<Grade> getGradesByTeacher(Long teacherId) {
        return gradeRepository.findByTeacherId(teacherId);
    }
}
