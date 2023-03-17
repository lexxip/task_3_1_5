package ru.preproject.task_3_1_5.service;

import ru.preproject.task_3_1_5.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listRoles();
    Role findRoleById(Long id);
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRoleById(Long id);
}
