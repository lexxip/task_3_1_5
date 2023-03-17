package ru.preproject.task_3_1_5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.preproject.task_3_1_5.model.Role;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}
