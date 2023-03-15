package ru.preproject.task_3_1_4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.preproject.task_3_1_4.model.Role;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
