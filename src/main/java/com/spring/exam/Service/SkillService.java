package com.spring.exam.Service;

import com.spring.exam.Models.Skill;
import com.spring.exam.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Flux<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Mono<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public Mono<Skill> saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Mono<Skill> updateSkill(Long id, Skill updatedSkill) {
        return skillRepository.findById(id)
                .flatMap(existingSkill -> {
                    existingSkill.setSkillName(updatedSkill.getSkillName());
                    return skillRepository.save(existingSkill);
                });
    }

    public Mono<Void> deleteSkill(Long id) {
        return skillRepository.deleteById(id);
    }
}
