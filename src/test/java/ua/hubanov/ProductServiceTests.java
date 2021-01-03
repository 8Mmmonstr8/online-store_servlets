package ua.hubanov;

import org.junit.Test;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.service.ProductService;

import java.util.List;

public class ProductServiceTests {
    ProductService productService = new ProductService();

    @Test
    public void getAllProductsTest() {
        List<Product> productList = productService.getAllProducts();
        productList.forEach(x -> System.out.println(x.getName()));
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = productService.getAllCategories();
        categories.forEach(x -> System.out.println(x.getId() + " " + x.getName()));
    }

    @Test
    public void createNewCategoryTest() {
        productService.createNewCategory("clothes");
    }
}
