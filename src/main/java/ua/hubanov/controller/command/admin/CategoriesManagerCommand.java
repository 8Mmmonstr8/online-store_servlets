package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class CategoriesManagerCommand implements Command {
    ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String userRole = (String) request.getSession().getAttribute("role");

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        request.setAttribute("categories", productService.getAllCategories());

        return "/views/admin/categories.jsp";
    }
}
