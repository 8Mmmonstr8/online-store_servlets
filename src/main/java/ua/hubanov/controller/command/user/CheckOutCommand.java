package ua.hubanov.controller.command.user;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;

import javax.servlet.http.HttpServletRequest;

public class CheckOutCommand implements Command {
    CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        cartService.checkOut(loggedUser);

        return "redirect:/store/user_home/cart";
    }
}
