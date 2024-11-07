package com.spring.exam.repository;

import com.spring.exam.Models.Teacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TeacherRepository extends ReactiveCrudRepository<Teacher, Long> {
    Flux<Teacher> findAll(); // Для асинхронного получения списка преподавателей
}
