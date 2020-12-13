package ua.hubanov.model.service;

import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.entity.Product;
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

}
