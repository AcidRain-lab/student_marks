package com.spring.exam.Service;

import com.spring.exam.Models.Teacher;
import com.spring.exam.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Flux<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    public Mono<Teacher> saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Mono<Teacher> updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id)
                .flatMap(existingTeacher -> {
                    existingTeacher.setFirstName(updatedTeacher.getFirstName());
                    existingTeacher.setLastName(updatedTeacher.getLastName());
                    existingTeacher.setBirthDate(updatedTeacher.getBirthDate());
                    existingTeacher.setPhone(updatedTeacher.getPhone());
                    existingTeacher.setEmail(updatedTeacher.getEmail());
                    return teacherRepository.save(existingTeacher);
                });
    }

    public Mono<Void> deleteTeacher(Long id) {
        return teacherRepository.deleteById(id);
    }
}
