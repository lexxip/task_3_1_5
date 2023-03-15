package ru.preproject.task_3_1_4.controller;

import org.springframework.web.bind.annotation.*;
import ru.preproject.task_3_1_4.model.User;
import ru.preproject.task_3_1_4.service.UserService;

@RestController
public class RestAPIController {
    private final UserService userService;

    public RestAPIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

}
