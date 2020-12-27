package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.dao.mapper.ProductMapper;
import ua.hubanov.model.entity.Product;

import java.sql.*;
import java.util.*;

public class JDBCProductDao implements ProductDao {
    private final Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product entity) {
        return false;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            ProductMapper productMapper = new ProductMapper();
            if (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                return Optional.of(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        Map<Long, Product> products = new HashMap<>();

        final String query = "SELECT * FROM products";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                product = productMapper.makeUnique(products, product);
            }
            return new ArrayList<>(products.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {

    }
}
