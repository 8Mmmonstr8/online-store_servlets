package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderDetailsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        OrderService orderService = new OrderService();
        Long orderId = Long.valueOf(request.getParameter("orderId"));

        List<OrderedProduct> orderedProductList = orderService.findAllOrderedProductsByOrderId(orderId);

        request.setAttribute("orderedProducts", orderedProductList);
        request.setAttribute("totalPrice", orderService.getTotal(orderedProductList));

        return "/views/admin/order.jsp";
    }
}
