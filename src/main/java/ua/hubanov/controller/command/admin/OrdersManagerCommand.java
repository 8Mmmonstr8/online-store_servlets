package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrdersManagerCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getAllOrders();

        request.setAttribute("notApprovedOrders", orderService.getAllNotApprovedOrders(orderList));
        request.setAttribute("approvedOrders", orderService.getAllApprovedOrders(orderList));

        return "/views/admin/orders.jsp";
    }
}
