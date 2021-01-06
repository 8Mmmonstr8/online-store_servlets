package ua.hubanov;

import org.junit.Test;
import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.service.OrderService;

import java.util.List;

public class OrderServiceTests {
    OrderService orderService = new OrderService();

    @Test
    public void getAllOrdersTest() {
        List<Order> orders = orderService.getAllOrders();
        orders.forEach(x -> System.out.println(x.getId() + " " + x.getUser().getId()
                        + " " + x.getCart().getId() + " " + x.getOrderDate()));
    }

    @Test
    public void getAllApprovedOrdersTest() {
        List<Order> orders = orderService.getAllOrders();
        List<Order> approvedOrders = orderService.getAllApprovedOrders(orders);
        approvedOrders.forEach(x -> System.out.println(x.getId() + " " + x.isApproved()));
    }

    @Test
    public void getAllNotApprovedOrdersTest() {
        List<Order> orders = orderService.getAllOrders();
        List<Order> notApprovedOrders = orderService.getAllNotApprovedOrders(orders);
        notApprovedOrders.forEach(x -> System.out.println(x.getId() + " " + x.isApproved()));
    }

    @Test
    public void findAllOrderedProductsByOrderIdTest() {
        List<OrderedProduct> orderedProductList = orderService.findAllOrderedProductsByOrderId(27L);
        orderedProductList.forEach(x -> System.out.println(x.getId() + " "
                + x.getCategory().getName() + " " + x.getOrder().getOrderDate()));
    }

    @Test
    public void getTotalTest() {
        List<OrderedProduct> orderedProductList = orderService.findAllOrderedProductsByOrderId(27L);
        System.out.println(orderService.getTotal(orderedProductList));
    }

    @Test
    public void cancelOrderTest() {
        orderService.cancelOrder(30L);
    }

    @Test
    public void approveOrderTest() {
        try {
            orderService.approveOrder(34L);
        } catch (StockQuantityIsNotEnoughException e) {
            e.printStackTrace();
        }
    }
}
