package com.spring.exam.repository;

import com.spring.exam.Models.TeacherSkill;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TeacherSkillRepository extends ReactiveCrudRepository<TeacherSkill, Long> {
    Flux<TeacherSkill> findByTeacherId(Long teacherId);
    Mono<Void> deleteByTeacherIdAndSkillId(Long teacherId, Long skillId);
}
