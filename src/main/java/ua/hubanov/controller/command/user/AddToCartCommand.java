package ua.hubanov.controller.command.user;

import ua.hubanov.controller.command.Command;
import ua.hubanov.exceptions.AlreadyInCartException;
import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;

import javax.servlet.http.HttpServletRequest;

public class AddToCartCommand implements Command {
    CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        Long productId = Long.valueOf(request.getParameter("productId"));

        //TODO Handle exceptions
        try {
            cartService.addProductToCart(loggedUser, productId);
        } catch (StockQuantityIsNotEnoughException | AlreadyInCartException e) {
            e.printStackTrace();
        }

        return "redirect:/store/user_home";
    }
}
