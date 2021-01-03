package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.MultipleMethodCommand;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;

public class CreateCategoryCommand extends MultipleMethodCommand {
    ProductService productService = new ProductService();

    @Override
    protected String performGet(HttpServletRequest request) {

        return "/views/admin/create_category.jsp";
    }

    // TODO validation for category name
    @Override
    protected String performPost(HttpServletRequest request) {
        String catName = request.getParameter("name");
        productService.createNewCategory(catName);

        return "redirect:/store/admin_home/products/categories";
    }
}
