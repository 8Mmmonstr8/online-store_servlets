package ua.hubanov.model.dao;

import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.entity.User;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email) throws UserNotFoundException;

}
