package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.dao.mapper.ProductMapper;
import ua.hubanov.model.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCProductDao implements ProductDao {
    private final Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Product entity) {

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        Map<Long, Product> products = new HashMap<>();

        final String query = " select * from products";
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
