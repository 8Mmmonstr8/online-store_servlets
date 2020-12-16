package ua.hubanov.model.dao.impl;

import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.dao.mapper.UserMapper;
import ua.hubanov.model.entity.User;

import java.sql.*;
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


        //SELECT * FROM user LEFT JOIN carts ON user.id=carts.user_id;
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
//        final String query = " select * from user where email = ?";
//        try (PreparedStatement pst = connection.prepareStatement(query)) {
//            final String queryString = "\'" + email + "\'";
//            pst.setString(1, queryString);
//            ResultSet rs = pst.executeQuery(query);
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
