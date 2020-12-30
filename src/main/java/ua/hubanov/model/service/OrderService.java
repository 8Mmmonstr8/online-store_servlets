package ua.hubanov.model.service;

import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllApprovedOrders(List<Order> orders);
    List<Order> getAllNotApprovedOrders(List<Order> orders);

    List<OrderedProduct> findAllOrderedProductsByOrderId(Long orderId);
    BigDecimal getTotal(List<OrderedProduct> orderedProducts);
    void cancelOrder(Long orderId);
}
