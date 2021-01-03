package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.Command;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductCommand implements Command {
    ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {

        Long productId = Long.valueOf(request.getParameter("productId"));

        productService.deleteProduct(productId);

        return "redirect:/store/admin_home/products";
    }
}
