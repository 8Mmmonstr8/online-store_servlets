package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.Order;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.OrderService;
import ua.hubanov.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CancelOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        orderService.cancelOrder(orderId);

        return "redirect:/store/admin_home/orders";
    }
}
