package ru.preproject.task_3_1_5.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.preproject.task_3_1_5.dao.RoleDao;
import ru.preproject.task_3_1_5.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> listRoles() {
        return roleDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Role findRoleById(Long id) {
        return roleDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        roleDao.save(role);
    }

    @Transactional
    @Override
    public void deleteRoleById(Long id) {
        roleDao.deleteById(id);
    }
}
