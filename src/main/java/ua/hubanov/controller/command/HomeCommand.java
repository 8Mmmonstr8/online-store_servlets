package ua.hubanov.controller.command;

import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HomeCommand implements Command {
    ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userRole = (String) session.getAttribute("role");

        request.setAttribute("role", userRole);
        request.setAttribute("products", productService.getAllProducts());

        return "/views/home.jsp";
    }
}
