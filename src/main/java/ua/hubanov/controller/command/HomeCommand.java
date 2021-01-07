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
        request.setAttribute("categories", productService.getAllCategories());
//        request.setAttribute("products", productService.getAllProducts());


        int currentPage;
        int recordsPerPage;
        String category = request.getParameter("category");
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

        if (category == null) {
            category = "All";
        }

        List<Product> products;
        int rows;
        if (category.equals("All")) {
            products = productService.getAllProductsWithLimit(currentPage, recordsPerPage);
            rows = productService.getNumberOfRows();
        } else {
            products = productService.getAllProductsByCategoryIdWithLimit
                    (Integer.parseInt(request.getParameter("category")), currentPage, recordsPerPage);
            rows = productService.getNumberOfRowsByCategory(Integer.parseInt(category));
        }

        request.setAttribute("user", loggedUser);
        request.setAttribute("role", userRole);

        int nOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("category", category);
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("products", products);

        return "/views/home.jsp";
    }
}
