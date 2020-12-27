package ua.hubanov.model.dao.impl;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.dao.CartDao;
import ua.hubanov.model.dao.mapper.CategoryMapper;
import ua.hubanov.model.dao.mapper.InCartProductMapper;
import ua.hubanov.model.dao.mapper.OrderedProductMapper;
import ua.hubanov.model.dao.mapper.OrdersMapper;
import ua.hubanov.model.entity.*;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class JDBCCartDao implements CartDao {
    CategoryMapper categoryMapper = new CategoryMapper();
    OrdersMapper ordersMapper = new OrdersMapper();
    private final Connection connection;

    public JDBCCartDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        String sql = "SELECT * FROM carts WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getLong("id"));
                return Optional.of(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
    public Map<Product, Integer> findAllProductsInCart(Cart cart) throws ProductNotFoundException, CartNotFoundException {
        Map<Long, InCartProduct> inCartProducts = new HashMap<>();

        String sql = "SELECT * FROM in_cart_product WHERE cart_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, cart.getId());
            ResultSet rs = ps.executeQuery();

            InCartProductMapper inCartProductMapper = new InCartProductMapper();
            while (rs.next()) {
                InCartProduct inCartProduct = inCartProductMapper.extractFromResultSet(rs);
                inCartProduct = inCartProductMapper.makeUnique(inCartProducts, inCartProduct);
            }
            return inCartProducts.values().stream()
                    .collect(Collectors.toMap(InCartProduct::getProduct
                            , InCartProduct::getNeededQuantity));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public Set<OrderedProduct> getAllOrderedProductsOfUser(User user) throws CartNotFoundException, SQLException, ProductNotFoundException {
        Map<Long, OrderedProduct> orderedProducts = new HashMap<>();


        String sql = "SELECT op.*, orders.*, categories.* FROM carts c, orders o, ordered_products op " +
                "INNER JOIN orders ON op.order_id = orders.id " +
                "INNER JOIN categories ON op.category_id = categories.id " +
                "WHERE c.id = ? AND o.cart_id = c.id AND op.order_id = o.id";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, user.getCart().getId());
            ResultSet rs = ps.executeQuery();

            OrderedProductMapper orderedProductMapper = new OrderedProductMapper();
            while (rs.next()) {
                OrderedProduct orderedProduct = orderedProductMapper.extractFromResultSet(rs);
                orderedProduct.setOrder(ordersMapper.extractFromResultSet(rs));
                orderedProduct.setCategory(categoryMapper.extractFromResultSet(rs));
                orderedProduct = orderedProductMapper.makeUnique(orderedProducts, orderedProduct);
            }
            return new HashSet<>(orderedProducts.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

