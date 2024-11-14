package com.spring.exam.service;

import com.spring.exam.Models.Student;
import com.spring.exam.Models.Teacher;
import com.spring.exam.Models.User;
import com.spring.exam.repository.StudentRepository;
import com.spring.exam.repository.TeacherRepository;
import com.spring.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public UserService(UserRepository userRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Получение учителя для пользователя
    @Transactional(readOnly = true)
    public Teacher getTeacherForUser(User user) {
        if (user.getTeacherId() != null) {
            return teacherRepository.findById(user.getTeacherId()).orElse(null);
        }
        return null;
    }

    // Получение студента для пользователя
    @Transactional(readOnly = true)
    public Student getStudentForUser(User user) {
        if (user.getStudentId() != null) {
            return studentRepository.findById(user.getStudentId()).orElse(null);
        }
        return null;
    }
}
