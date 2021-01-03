package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.dao.mapper.CategoryMapper;
import ua.hubanov.model.dao.mapper.ProductMapper;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;

import java.sql.*;
import java.util.*;

public class JDBCProductDao implements ProductDao {
    CategoryMapper categoryMapper = new CategoryMapper();
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
        String sql = "SELECT * FROM products INNER JOIN categories ON products.category_id = categories.id " +
                "WHERE products.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            connection.setAutoCommit(true);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            ProductMapper productMapper = new ProductMapper();
            if (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                product.setCategory(categoryMapper.extractFromResultSet(rs));
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

        final String query = "SELECT * FROM products INNER JOIN categories ON products.category_id = categories.id ";
        try (Statement st = connection.createStatement()) {
            connection.setAutoCommit(true);
            ResultSet rs = st.executeQuery(query);

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                product.setCategory(categoryMapper.extractFromResultSet(rs));
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = categoryMapper.extractFromResultSet(rs);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createNewCategory(String catName) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, catName);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
