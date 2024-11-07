package com.spring.exam.repository;


import com.spring.exam.Models.Skill;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends ReactiveCrudRepository<Skill, Long> {
}
