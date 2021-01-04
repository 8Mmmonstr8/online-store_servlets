package ua.hubanov.model.service;

import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllApprovedOrders(List<Order> orders);
    List<Order> getAllNotApprovedOrders(List<Order> orders);

    List<OrderedProduct> findAllOrderedProductsByOrderId(Long orderId);
    BigDecimal getTotal(List<OrderedProduct> orderedProducts);
    void cancelOrder(Long orderId);
    void approveOrder(Long orderId) throws StockQuantityIsNotEnoughException;
}
