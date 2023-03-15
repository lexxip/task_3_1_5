package ru.preproject.task_3_1_4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.preproject.task_3_1_4.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
