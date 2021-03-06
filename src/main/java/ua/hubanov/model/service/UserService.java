package ua.hubanov.model.service;

import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.entity.User;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public User findUserByEmail(String email) throws UserNotFoundException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByEmail(email);
        }
    }

    public List<User> getAllUsers() {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public boolean saveUser(User newUser) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(newUser);
        }
    }

    public void blockUser(Long userId) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.blockUser(userId);
        }
    }

    public void unblockUser(Long userId) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.unblockUser(userId);
        }
    }

    public void deleteUser(Long userId) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.delete(userId);
        }
    }
}
