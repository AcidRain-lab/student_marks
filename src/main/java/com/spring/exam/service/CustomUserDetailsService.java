package com.spring.exam.service;

import com.spring.exam.Models.User;
import com.spring.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Получаем пользователя из репозитория через Optional
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Создаем объект UserDetails из модели User
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // Устанавливаем имя пользователя
                .password(user.getPassword())     // Устанавливаем пароль
                .roles(user.getRole().name())     // Устанавливаем роль как строку (например, TEACHER или STUDENT)
                .build();
    }*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Загружаем пользователя из базы данных
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Возвращаем UserDetails без проверки пароля
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // Устанавливаем имя пользователя
                .password("")                     // Пароль не используется
                .roles(user.getRole().name())     // Роли пользователя
                .build();
    }
}



