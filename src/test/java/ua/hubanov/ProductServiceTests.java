package ua.hubanov;

import org.junit.Test;
import ua.hubanov.model.entity.Category;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.service.ProductService;

import java.math.BigDecimal;
import java.util.Date;
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

    @Test
    public void createNewProductTest() {
        Product product = new Product();
        product.setName("Apple");
        product.setDescription("fresh apple");
        Category category = new Category();
        category.setId(3L);
        category.setName("Gadgets");
        product.setCategory(category);
        product.setPrice(BigDecimal.valueOf(10.0d));
        product.setQuantity(1000);
        product.setPublicationDate(new Date());

        productService.createNewProduct(product);


    }

    @Test
    public void deleteProductTest() {
        productService.deleteProduct(15L);
    }

    @Test
    public void getAllProductWithLimitTest() {
        List<Product> products = productService.getAllProductsWithLimit(3, 5);
        products.forEach(x -> System.out.println(x.getId() + " " + x.getCategory().getName()));
    }

    @Test
    public void findAllProducts() {
        List<Product> products = productService.getAllProducts();
        products.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getCategory().getName()));
    }

    @Test
    public void getNumberOfRowsTest() {
        int numOfRows = productService.getNumberOfRows();
        System.out.println(numOfRows);
    }
}
