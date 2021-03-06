package ua.hubanov.controller.command.user;

import ua.hubanov.controller.command.Command;
import ua.hubanov.controller.command.MultipleMethodCommand;
import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

public class UserCartCommand extends MultipleMethodCommand {
    CartService cartService = new CartService();

    @Override
    protected String performGet(HttpServletRequest request) {
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

        Map<Product, Integer> inCartProducts = cartService.getAllProductsInCart(loggedUser);
        Set<OrderedProduct> orderedProducts = cartService.getAllOrderedProductsOfUser(loggedUser);

        request.setAttribute("products", inCartProducts);
        request.setAttribute("totalPrice", cartService.getTotal(inCartProducts));
        request.setAttribute("approvedOrderedProducts", cartService.getAllApprovedOrderedProductsOfUser(orderedProducts));
        request.setAttribute("notApprovedOrderedProducts", cartService.getAllNotApprovedOrderedProductsOfUser(orderedProducts));


        return "/views/user/cart.jsp";
    }

    @Override
    protected String performPost(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        Long productId = Long.valueOf(request.getParameter("productId"));
        Integer neededQuantity = Integer.valueOf(request.getParameter("neededQuantity"));

        // TODO handle exception
        try {
            cartService.updateNeededQuantity(loggedUser, productId, neededQuantity);
        } catch (StockQuantityIsNotEnoughException e) {
            e.printStackTrace();
        }

        return "redirect:/store/user_home/cart";
    }
}





/*
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

        Map<Product, Integer> inCartProducts = cartService.getAllProductsInCart(loggedUser);
        Set<OrderedProduct> orderedProducts = cartService.getAllOrderedProductsOfUser(loggedUser);

        request.setAttribute("products", inCartProducts);
        request.setAttribute("totalPrice", cartService.getTotal(inCartProducts));
        request.setAttribute("approvedOrderedProducts", cartService.getAllApprovedOrderedProductsOfUser(orderedProducts));
        request.setAttribute("notApprovedOrderedProducts", cartService.getAllNotApprovedOrderedProductsOfUser(orderedProducts));


        return "/views/user/cart.jsp";
    }
}
*/
