package com.spring.exam.service;

import com.spring.exam.Models.Student;
import com.spring.exam.repository.StudentRepository;
import org.springframework.stereotype.Service;import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


import com.spring.exam.Models.Grade;
import com.spring.exam.Models.Student;
import com.spring.exam.repository.GradeRepository;
import com.spring.exam.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    // --- Методы для работы с студентами ---

    // Получение всех студентов
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Получение студента по ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Сохранение нового студента
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Обновление существующего студента
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    // Удаление студента
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // --- Методы для работы с оценками студентов ---

    // Получение всех оценок студента
    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}
