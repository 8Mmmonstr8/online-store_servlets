package ua.hubanov;

import org.junit.Test;
import ua.hubanov.exceptions.AlreadyInCartException;
import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.entity.User;
import ua.hubanov.model.service.CartService;
import ua.hubanov.model.service.UserService;

public class CartServiceTests {
    CartService cartService = new CartService();
    UserService userService = new UserService();

    @Test
    public void addProductToCartTest() {
        User user = userService.findUserByEmail("test5@test5");
        try {
            cartService.addProductToCart(user, 1L);
        } catch (StockQuantityIsNotEnoughException | AlreadyInCartException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateNeededQuantityTest() {
        User user = userService.findUserByEmail("test5@test5");

        try {
            cartService.updateNeededQuantity(user, 3L, 134);
        } catch (StockQuantityIsNotEnoughException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteProductFromCartTest() {
        User user = userService.findUserByEmail("test5@test5");

        cartService.deleteProductFromCart(user, 3L);
    }

    @Test
    public void checkOutTest() {
        User user = userService.findUserByEmail("test5@test5");

        try {
            cartService.checkOut(user);
        } catch (StockQuantityIsNotEnoughException e) {
            e.printStackTrace();
        }
    }
}
