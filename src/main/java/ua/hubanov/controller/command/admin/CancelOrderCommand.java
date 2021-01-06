package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        orderService.cancelOrder(orderId);

        return "redirect:/store/admin_home/orders";
    }
}
