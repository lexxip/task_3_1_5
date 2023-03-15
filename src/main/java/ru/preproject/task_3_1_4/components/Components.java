package ru.preproject.task_3_1_4.components;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.preproject.task_3_1_4.model.Role;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Components {
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
