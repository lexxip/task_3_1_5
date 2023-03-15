package ru.preproject.task_3_1_4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.preproject.task_3_1_4.service.UserService;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig{

    private final UserService userService;
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(UserService userService, SuccessUserHandler successUserHandler) {
        this.userService = userService;
        this.successUserHandler = successUserHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/index", "/js/*").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/login").anonymous()
                        .requestMatchers("/logout").authenticated()
                        .anyRequest().denyAll())
                .formLogin()
                .successHandler(successUserHandler)
                .and()
                .logout()
                .and()
                .userDetailsService(userService)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
