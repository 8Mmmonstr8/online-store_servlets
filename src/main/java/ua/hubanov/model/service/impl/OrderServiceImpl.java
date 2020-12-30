package ua.hubanov.model.service.impl;

import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.OrderDao;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.service.OrderService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Order> getAllOrders() {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.findAll();
        }
    }

    @Override
    public List<Order> getAllApprovedOrders(List<Order> orders) {
        return orders.stream().filter(Order::isApproved).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllNotApprovedOrders(List<Order> orders) {
        return orders.stream().filter(x -> !x.isApproved()).collect(Collectors.toList());
    }

    @Override
    public List<OrderedProduct> findAllOrderedProductsByOrderId(Long orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.findAllOrderedProductsByOrderId(orderId);
        }
    }

    @Override
    public BigDecimal getTotal(List<OrderedProduct> orderedProducts) {
        return orderedProducts.stream().map(x -> x.getPrice().multiply(BigDecimal.valueOf(x.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void cancelOrder(Long orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.cancelOrder(orderId);
        }
    }
}
