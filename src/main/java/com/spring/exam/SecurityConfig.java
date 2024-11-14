package com.spring.exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final boolean enableSecurity = false; // Управляет использованием Spring Security

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (enableSecurity) {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/teacher/**").hasRole("TEACHER")
                            .requestMatchers("/student/**").hasRole("STUDENT")
                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/")
                            .permitAll()
                    )
                    .logout(logout -> logout.permitAll());
        } else {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(requests -> requests
                            .anyRequest().permitAll()
                    );
        }
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Используем NoOpPasswordEncoder для незашифрованных паролей
        return NoOpPasswordEncoder.getInstance();
    }
}
