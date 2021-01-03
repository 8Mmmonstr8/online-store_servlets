package ua.hubanov.controller.command.admin;

import ua.hubanov.controller.command.MultipleMethodCommand;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;

public class EditProductCommand extends MultipleMethodCommand {
    ProductService productService = new ProductService();

    @Override
    protected String performGet(HttpServletRequest request) {
        Long productId = Long.valueOf(request.getParameter("productId"));
        request.setAttribute("categories", productService.getAllCategories());
        try {
            request.setAttribute("product", productService.findById(productId));
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }


        return "/views/admin/edit_product.jsp";
    }

    @Override
    protected String performPost(HttpServletRequest request) {
        Long productId = Long.valueOf(request.getParameter("productId"));

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

        productService.updateProduct(product, productId);

        return "redirect:/store/admin_home/products";
    }
}
