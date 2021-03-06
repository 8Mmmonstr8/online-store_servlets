package ua.hubanov.model.dao;

import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<OrderedProduct> findAllOrderedProductsByOrderId(Long orderId);
    void cancelOrder(Long orderId);
    void approveOrder(Long orderId) throws StockQuantityIsNotEnoughException;
}
