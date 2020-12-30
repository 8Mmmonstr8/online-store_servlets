package ua.hubanov;


import org.junit.Test;
import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.dao.impl.JDBCCartDao;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;
import ua.hubanov.model.service.ProductService;
import ua.hubanov.model.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainTest {
    UserService userService = new UserService();
    ProductService productService = new ProductService();

    @Test
    public void findProductById() {
        Product product = new Product();
        try {
            product = productService.findById(4L);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(product.getName() + product.getCategory().getName());
    }

    @Test
    public void findAllProducts() {
        List<Product> products = productService.getAllProducts();
        products.forEach(x -> System.out.println(x.getName() + " " + x.getCategory().getName()));
    }

    @Test
    public void findByEmailTest() throws Exception {
        User user = userService.findUserByEmail("admin@admin.com");
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        System.out.println(user.getRole());
    }

    @Test
    public void findAllUsersTest() {
        List<User> users = userService.getAllUsers();
        users.stream().forEach(x -> System.out.println(x.getId() + " " + x.getCart().getId()));
    }

    @Test
    public void createNewCartTest() {
        CartService cartService = new CartService();
        Long cartId = cartService.createNewCart(new Cart());
        System.out.println(cartId);
    }

    @Test
    public void findCartByIdTest() {
        CartService cartService = new CartService();
        try {
            Cart cart = cartService.findById(12L);
            System.out.println(cart.getId());
        } catch (CartNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findAllProductsInCartTest() {
        CartService cartService = new CartService();
        User user = userService.findUserByEmail("test5@test5");

        Map<Product, Integer> productInCart = cartService.getAllProductsInCart(user);
        for (Map.Entry<Product, Integer> x : productInCart.entrySet()) {
            System.out.println(x.getKey().getName() + " " + x.getValue());
        }
    }

    @Test
    public void getAllProductsTest() {
        ProductService productService = new ProductService();
        List<Product> products = productService.getAllProducts();
        products.forEach(x -> System.out.println(x.getName()));
    }

    @Test
    public void getAllOrderedProductsOfUserTest() {
        CartService cartService = new CartService();
        User user = userService.findUserByEmail("test5@test5");

        Set<OrderedProduct> orderedProducts = cartService.getAllOrderedProductsOfUser(user);
        orderedProducts.forEach(x -> System.out.println(x.getId() + " " + x.getCategory().getName() + " "
                            + x.getOrder().getOrderDate() + " " + x.getOrder().isApproved()));
    }

    @Test
    public void getAllApprovedOrderedProductsOfUserTest() {
        CartService cartService = new CartService();
        User user = userService.findUserByEmail("test5@test5");
        List<OrderedProduct> orderedProducts = cartService.getAllApprovedOrderedProductsOfUser(cartService.getAllOrderedProductsOfUser(user));
        orderedProducts.forEach(x -> System.out.println(x.getId() + " " + x.getOrder().getId()));
    }

    @Test
    public void getTotalSumTest() {
        CartService cartService = new CartService();
        User user = userService.findUserByEmail("test5@test5");

        Map<Product, Integer> inCartProducts = cartService.getAllProductsInCart(user);
        System.out.println(cartService.getTotal(inCartProducts));
    }

}
