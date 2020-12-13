package ua.hubanov.model.dao.impl;

import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.dao.mapper.ProductMapper;
import ua.hubanov.model.dao.mapper.UserMapper;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDao implements UserDao {
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Map<Long, User> users = new HashMap<>();

        final String query = " select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        List<User> users = findAll();
        User user = users.stream()
                .filter(x -> email.equals(x.getEmail()))
                .findAny().orElseThrow(UserNotFoundException::new);
        return user;

//        User user = new User();
//
//        final String query = " select * from user where email = '" + email + "'";
//        try (Statement st = connection.createStatement()) {
//            ResultSet rs = st.executeQuery(query);
//
//            UserMapper userMapper = new UserMapper();
//            user = userMapper.extractFromResultSet(rs);
//
//            return user;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
}
