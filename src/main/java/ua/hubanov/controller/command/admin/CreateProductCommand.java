package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.MultipleMethodCommand;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;

public class CreateProductCommand extends MultipleMethodCommand {
    ProductService productService = new ProductService();

    @Override
    protected String performGet(HttpServletRequest request) {
        request.setAttribute("categories", productService.getAllCategories());

        return "/views/admin/product_form.jsp";
    }

    // TODO set validation and adjust method
    @Override
    protected String performPost(HttpServletRequest request) {

        String description = request.getParameter("description");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String date = request.getParameter("date");
//        String categoryName = request.getParameter("category");
        Long categoryId = Long.valueOf(request.getParameter("category"));

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        product.setQuantity(Integer.valueOf(quantity));
        product.setPublicationDate(Date.valueOf(date));
        Category category = new Category();
//        category.setName(categoryName);
        category.setId(categoryId);
        product.setCategory(category);

        productService.createNewProduct(product);

        return "redirect:/store/admin_home/products";
    }
}
