package ua.hubanov.model.dao.impl;

import ua.hubanov.model.dao.OrderDao;
import ua.hubanov.model.dao.mapper.CategoryMapper;
import ua.hubanov.model.dao.mapper.OrderMapper;
import ua.hubanov.model.dao.mapper.OrderedProductMapper;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;

import java.sql.*;
import java.util.*;

public class JDBCOrderDao implements OrderDao {
    private final Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        Map<Long, Order> orders = new HashMap<>();

        final String query = "SELECT * FROM orders";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            OrderMapper orderMapper = new OrderMapper();

            while (rs.next()) {
                Order order = orderMapper.extractFromResultSet(rs);
                order = orderMapper.makeUnique(orders, order);
            }

            List<Order> orderList = new ArrayList<>(orders.values());
            orderList.sort(Comparator.comparing(Order::getId));
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {
        try{
            connection.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderedProduct> findAllOrderedProductsByOrderId(Long orderId) {
        Map<Long, OrderedProduct> orderedProducts = new HashMap<>();


        String sql = "SELECT op.*, orders.*, categories.* FROM orders o, ordered_products op " +
                "INNER JOIN orders ON op.order_id = orders.id " +
                "INNER JOIN categories ON op.category_id = categories.id " +
                "WHERE o.id = ? AND op.order_id = o.id";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            OrderedProductMapper orderedProductMapper = new OrderedProductMapper();
            OrderMapper orderMapper = new OrderMapper();
            CategoryMapper categoryMapper = new CategoryMapper();
            while (rs.next()) {
                OrderedProduct orderedProduct = orderedProductMapper.extractFromResultSet(rs);
                orderedProduct.setOrder(orderMapper.extractFromResultSet(rs));
                orderedProduct.setCategory(categoryMapper.extractFromResultSet(rs));
                orderedProduct = orderedProductMapper.makeUnique(orderedProducts, orderedProduct);
            }

            List<OrderedProduct> orderedProductList = new ArrayList<>(orderedProducts.values());
            orderedProductList.sort(Comparator.comparing(OrderedProduct::getId));
            return orderedProductList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cancelOrder(Long orderId) {
        String sql = "START TRANSACTION";
        String sql1 = "INSERT INTO in_cart_product (cart_id, product_id, needed_quantity) " +
                "SELECT c.id, op.product_id, op.quantity FROM carts c, ordered_products op, orders o " +
                "WHERE op.order_id = ? AND c.id = o.cart_id AND op.order_id = o.id";
        String sql2 = "DELETE FROM ordered_products WHERE order_id = ?";
        String sql3 = "DELETE FROM orders WHERE id = ?";
        String sql4 = "COMMIT";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             PreparedStatement ps1 = connection.prepareStatement(sql1);
             PreparedStatement ps2 = connection.prepareStatement(sql2);
             PreparedStatement ps3 = connection.prepareStatement(sql3);
             PreparedStatement ps4 = connection.prepareStatement(sql4)) {
            connection.setAutoCommit(false);
            ps1.setLong(1, orderId);
            ps2.setLong(1, orderId);
            ps3.setLong(1, orderId);

            ps.execute();
            ps1.execute();
            ps2.execute();
            ps3.execute();
            ps4.execute();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
