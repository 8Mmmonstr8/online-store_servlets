package ua.hubanov.model.dao.mapper;

import ua.hubanov.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrdersMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("orders.id"));
        order.setOrderDate(rs.getDate("orders.date"));
//        order.setUser();
//        order.setCart();
        order.setApproved(rs.getBoolean("orders.is_approved"));
        return order;
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order entity) {
        return null;
    }
}
