package ua.hubanov.model.dao.mapper;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderedProductMapper implements ObjectMapper<OrderedProduct> {
    @Override
    public OrderedProduct extractFromResultSet(ResultSet rs) throws SQLException, ProductNotFoundException, CartNotFoundException {
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setId(rs.getLong("id"));
        orderedProduct.setDescription(rs.getString("description"));
        orderedProduct.setName(rs.getString("name"));
        orderedProduct.setPrice(rs.getBigDecimal("price"));
        orderedProduct.setQuantity(rs.getInt("quantity"));
//        orderedProduct.setCategory();
        orderedProduct.setOrder(new Order(rs.getLong("order_id")));
        return orderedProduct;
    }

    @Override
    public OrderedProduct makeUnique(Map<Long, OrderedProduct> cache, OrderedProduct orderedProduct) {
        cache.putIfAbsent(orderedProduct.getId(), orderedProduct);
        return cache.get(orderedProduct.getId());
    }
}
