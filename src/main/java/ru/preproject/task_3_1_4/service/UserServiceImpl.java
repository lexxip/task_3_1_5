package ru.preproject.task_3_1_4.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.preproject.task_3_1_4.dao.UserDao;
import ru.preproject.task_3_1_4.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        String password = user.getPassword();
        if (password.trim().length() == 0) {
            password = userDao.findById(user.getId()).orElse(null).getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(password));
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDao.save(user);
    }
//    @Override
//    @Transactional
//    public void updateUser(User user) {
//        User userDB = userDao.findById(user.getId()).orElse(null);
//        String password = user.getPassword();
//        if (password.trim().length() == 0) {
//            password = userDB.getPassword();
//            user.setPassword(bCryptPasswordEncoder.encode(password));
//        } else {
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }
//        userDB.setFirstName(user.getFirstName());
//        userDB.setLastName(user.getLastName());
//        userDB.setAge(user.getAge());
//        userDB.setEmail(user.getEmail());
//        userDB.setRoles(user.getRoles());
//        userDao.save(userDB);
//    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.delete(findUserById(id));
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User \"{username}\" not found!", username));
        }
        return user;
    }

}
