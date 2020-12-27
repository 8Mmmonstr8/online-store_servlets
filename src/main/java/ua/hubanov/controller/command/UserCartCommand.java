package ua.hubanov.controller.command;

import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class UserCartCommand implements Command {
    CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User loggedUser = (User) session.getAttribute("loggedUser");
        String userRole = (String) session.getAttribute("role");

        // Если еще не вошел в систему (login).
        if (loggedUser == null) {
            // Redirect (Перенаправить) к странице login.
            return "redirect:/store/login";
        }

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        Set<OrderedProduct> orderedProducts = cartService.getAllOrderedProductsOfUser(loggedUser);

        request.setAttribute("products", cartService.getAllProductsInCart(loggedUser));
//        request.setAttribute("totalPrice", cartService.getTotal(cartService.getAllProductsInCart(loggedUser)));
        request.setAttribute("approvedOrderedProducts", cartService.getAllApprovedOrderedProductsOfUser(orderedProducts));
        request.setAttribute("notApprovedOrderedProducts", cartService.getAllNotApprovedOrderedProductsOfUser(orderedProducts));


        return "/views/user/cart.jsp";
    }
}