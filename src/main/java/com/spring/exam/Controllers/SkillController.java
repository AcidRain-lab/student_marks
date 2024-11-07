package com.spring.exam.Controllers;

import com.spring.exam.Models.Skill;
import com.spring.exam.Service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public Mono<String> getAllSkills(Model model) {
        return skillService.getAllSkills()
                .collectList()  // Собираем Flux<Skill> в List<Skill>
                .doOnNext(skills -> model.addAttribute("skills", skills))
                .thenReturn("skills/list");  // Возвращаем название шаблона после завершения операции
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "skills/create";
    }

    @PostMapping("/create")
    public Mono<String> createSkill(@ModelAttribute Skill skill) {
        return skillService.saveSkill(skill)
                .thenReturn("redirect:/skills");  // Перенаправление после сохранения
    }

    @GetMapping("/edit/{id}")
    public Mono<String> showEditForm(@PathVariable Long id, Model model) {
        return skillService.getSkillById(id)
                .flatMap(skill -> {
                    model.addAttribute("skill", skill);
                    return Mono.just("skills/edit");
                })
                .switchIfEmpty(Mono.just("redirect:/skills"));
    }

    @PostMapping("/edit/{id}")
    public Mono<String> updateSkill(@PathVariable Long id, @ModelAttribute Skill skill) {
        return skillService.updateSkill(id, skill)
                .thenReturn("redirect:/skills");  // Перенаправление после обновления
    }

    @GetMapping("/delete/{id}")
    public Mono<String> deleteSkill(@PathVariable Long id) {
        return skillService.deleteSkill(id)
                .thenReturn("redirect:/skills");  // Перенаправление после удаления
    }
}
