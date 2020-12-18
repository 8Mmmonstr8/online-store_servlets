package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.CartDao;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.service.CartService;

import java.sql.*;
import java.util.List;

public class JDBCCartDao implements CartDao {
    private final Connection connection;

    public JDBCCartDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long createNewCart(Cart newCart) {
        String sql = "INSERT INTO carts values( )";


  //      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(Cart entity) {
        return false;
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public void update(Cart entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }
}
