package ru.preproject.task_3_1_5.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.preproject.task_3_1_5.model.User;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findUserById(Long id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    void deleteUser(User user);
}
