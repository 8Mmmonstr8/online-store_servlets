package ua.hubanov.controller.command;

import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomeCommand implements Command {
    ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loggedUser = (User) session.getAttribute("loggedUser");
        String userRole = (String) session.getAttribute("role");

        request.setAttribute("role", userRole);
//        request.setAttribute("products", productService.getAllProducts());


        int currentPage;
        int recordsPerPage;
        if (request.getParameter("currentPage") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        if (request.getParameter("recordsPerPage") == null) {
            recordsPerPage = 10;
        } else {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        }

        List<Product> products = productService.getAllProductsWithLimit(currentPage, recordsPerPage);

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);
        request.setAttribute("products", products);

        int rows = productService.getNumberOfRows();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);


        return "/views/home.jsp";
    }
}
