package ua.hubanov;


import org.junit.Test;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;
import ua.hubanov.model.service.UserService;

import java.util.List;

public class MainTest {
    UserService userService = new UserService();

    @Test
    public void findByEmailTest() throws Exception {
        User user = userService.findUserByEmail("admin@admin.com");
        System.out.println(user.getId());
        System.out.println(user.getRole());
        System.out.println(user.getRole().getClass());
    }

    @Test
    public void findAllUsersTest() {
        List<User> users = userService.getAllUsers();
        users.stream().forEach(x -> System.out.println(x.getId()));
    }

    @Test
    public void createNewCartTest() {
        CartService cartService = new CartService();
        Long cartId = cartService.createNewCart(new Cart());
        System.out.println(cartId);
    }
}
