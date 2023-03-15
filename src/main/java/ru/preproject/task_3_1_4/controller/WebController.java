package ru.preproject.task_3_1_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.preproject.task_3_1_4.model.User;
import ru.preproject.task_3_1_4.service.RoleService;
import ru.preproject.task_3_1_4.service.UserService;

import java.security.Principal;

@Controller
public class WebController {

    private final UserService userService;
    private final RoleService roleService;

    public WebController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String admin(@ModelAttribute("createuser") User createuser, Principal principal, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("authenticateduser", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.listRoles());
        return "source";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("createuser") User createuser, BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "source";
        userService.addUser(createuser);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        model.addAttribute("authenticateduser", userService.loadUserByUsername(principal.getName()));
        return "source";
    }

    @DeleteMapping("/admin")
    public String delete(@ModelAttribute("edituser") User edituser) {
        userService.deleteUser(edituser);
        return "redirect:/admin";
    }

    @PatchMapping("/admin")
    public String update(@ModelAttribute("edituser") User edituser) { //, BindingResult bindingResult   @Valid
//        if (bindingResult.hasErrors())
//            return "update";
        userService.updateUser(edituser);
        return "redirect:/admin";
    }


}
