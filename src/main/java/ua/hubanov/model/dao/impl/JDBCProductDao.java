package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.ProductDao;
import ua.hubanov.model.dao.mapper.CategoryMapper;
import ua.hubanov.model.dao.mapper.ProductMapper;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCProductDao implements ProductDao {
    CategoryMapper categoryMapper = new CategoryMapper();
    private final Connection connection;

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Product product) {
        String sql = "INSERT INTO products (description, name, price, date, quantity, category_id) " +
                "VALUES (?, ?, ?, ?, ? ,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getDescription());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
//            ps.setDate(4, Date.valueOf(product.getPublicationDate()));
            ps.setDate(4, new Date(product.getPublicationDate().getTime()));
            ps.setInt(5, product.getQuantity());
            ps.setLong(6, product.getCategory().getId());

            ps.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
    public List<Product> getAllProductWithLimit(int currentPage, int recordsPerPage) {
        List<Product> products = new ArrayList<>();
        final String query = "SELECT * FROM products INNER JOIN categories " +
                "ON products.category_id = categories.id ORDER BY products.id LIMIT ?, ?";
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, start);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                product.setCategory(categoryMapper.extractFromResultSet(rs));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getAllProductByCategoryWithLimit(int categoryId, int currentPage, int recordsPerPage) {
        List<Product> products = new ArrayList<>();
        final String query = "SELECT * FROM products INNER JOIN categories " +
                "ON products.category_id = categories.id " +
                "WHERE products.category_id = ? ORDER BY products.id LIMIT ?, ?";
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, start);
            ps.setInt(3, recordsPerPage);
            ResultSet rs = ps.executeQuery();

            ProductMapper productMapper = new ProductMapper();

            while (rs.next()) {
                Product product = productMapper.extractFromResultSet(rs);
                product.setCategory(categoryMapper.extractFromResultSet(rs));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getNumberOfRows() {
        int numOfRows = 0;
        String sql = "SELECT COUNT(ID) FROM products";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            numOfRows = rs.getInt(1);
            return numOfRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return numOfRows;
        }
    }

    @Override
    public int getNumberOfRowsByCategory(int categoryId) {
        int numOfRows = 0;
        String sql = "SELECT COUNT(ID) FROM products WHERE category_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            numOfRows = rs.getInt(1);
            return numOfRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return numOfRows;
        }
    }

    @Override
    public void update(Product entity) {

    }

    @Override
    public void delete(Long productId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, productId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public void updateProduct(Product product, Long productId) {
        String sql = "UPDATE products SET description = ?, name = ?, price = ?, date = ?, quantity = ?, category_id = ? " +
                "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getDescription());
            ps.setString(2, product.getName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setDate(4, new Date(product.getPublicationDate().getTime()));
            ps.setInt(5, product.getQuantity());
            ps.setLong(6, product.getCategory().getId());
            ps.setLong(7, productId);

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
