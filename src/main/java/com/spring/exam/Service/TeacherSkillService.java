package com.spring.exam.Service;

import com.spring.exam.Models.TeacherSkill;
import com.spring.exam.repository.TeacherSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherSkillService {

    @Autowired
    private TeacherSkillRepository teacherSkillRepository;

    public Flux<TeacherSkill> getSkillsByTeacherId(Long teacherId) {
        return teacherSkillRepository.findByTeacherId(teacherId);
    }

    public Mono<Void> addSkillToTeacher(Long teacherId, Long skillId) {
        TeacherSkill teacherSkill = new TeacherSkill(null, teacherId, skillId);
        return teacherSkillRepository.save(teacherSkill).then();
    }

    public Mono<Void> removeSkillFromTeacher(Long teacherId, Long skillId) {
        return teacherSkillRepository.deleteByTeacherIdAndSkillId(teacherId, skillId);
    }
}
