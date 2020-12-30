package ua.hubanov.model.dao.mapper;

import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("orders.id"));
        order.setOrderDate(rs.getDate("orders.date"));
        User user = new User();
        user.setId(rs.getLong("orders.user_id"));
        order.setUser(user);
        Cart cart = new Cart();
        cart.setId(rs.getLong("orders.cart_id"));
        order.setCart(cart);
        order.setApproved(rs.getBoolean("orders.is_approved"));
        return order;
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order order) {
        cache.putIfAbsent(order.getId(), order);
        return cache.get(order.getId());
    }
}
