package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ApproveOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        Long orderId = Long.valueOf(request.getParameter("orderId"));

        //TODO handle exception
        try {
            orderService.approveOrder(orderId);
        } catch (StockQuantityIsNotEnoughException e) {
            e.printStackTrace();
        }

        return "redirect:/store/admin_home/orders";
    }
}
