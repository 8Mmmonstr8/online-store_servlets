package ua.hubanov.model.dao.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.hubanov.exceptions.UserNotFoundException;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.dao.mapper.UserMapper;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;
    private final CartService cartService = new CartService();

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User user) {
        final String sql = "INSERT INTO User " +
                "(email, first_name, last_name, password, role, is_non_locked, cart_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

 //       final String INSERT_QUERY = "INSERT INTO user VALUES (DEFAULT,?,?,?,?,?,?, DEFAULT)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().name());
            statement.setBoolean(6, user.isNonLocked());
            statement.setLong(7, cartService.createNewCart(new Cart()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            LOGGER.info("user created");
            return true;
        } catch (SQLException e) {
            LOGGER.error("User was not created", e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
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
                //                user.setCart(cartService.findById(rs.getLong("cart_id")));
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
    public void delete(Long userId) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.execute();
            LOGGER.info("user deleted");
        } catch (SQLException e) {
            LOGGER.error("user has not been deleted", e);
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try{
            connection.close();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        List<User> users = findAll();
        User user = users.stream()
                .filter(x -> email.equals(x.getEmail()))
                .findAny().orElseThrow(UserNotFoundException::new);
        LOGGER.info("user has benn founded");
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

    @Override
    public void blockUser(Long userId) {
        String sql = "UPDATE user SET is_non_locked = FALSE WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.execute();
            LOGGER.info("user has been blocked");
        } catch (SQLException e) {
            LOGGER.error("user has not been blocked", e);
            e.printStackTrace();
        }
    }

    @Override
    public void unblockUser(Long userId) {
        String sql = "UPDATE user SET is_non_locked = TRUE WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.execute();
            LOGGER.info("user has been unblocked");
        } catch (SQLException e) {
            LOGGER.error("user has not been unblocked", e);
            e.printStackTrace();
        }
    }
}
