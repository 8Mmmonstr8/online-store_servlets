package ua.hubanov.controller.command.user;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.entity.UserRole;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserHomeCommand implements Command {
    ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // Проверить, вошел ли пользователь в систему (login) или нет.
        User loggedUser = (User) session.getAttribute("loggedUser");
        String userRole = (String) session.getAttribute("role");

        // Если еще не вошел в систему (login).
        if (loggedUser == null) {
            // Redirect (Перенаправить) к странице login.
            return "redirect:/store/login";
        }
        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        request.setAttribute("products", productService.getAllProducts());


        return "/views/user/user_home.jsp";
    }
}