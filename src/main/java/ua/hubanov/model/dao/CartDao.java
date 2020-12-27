package ua.hubanov.model.dao;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.OrderedProduct;
import ua.hubanov.model.entity.Product;
import ua.hubanov.model.entity.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public interface CartDao extends GenericDao<Cart> {
    public Long createNewCart(Cart newCart);

    Map<Product, Integer> findAllProductsInCart(Cart cart) throws ProductNotFoundException, CartNotFoundException;

    Set<OrderedProduct> getAllOrderedProductsOfUser(User user) throws CartNotFoundException, SQLException, ProductNotFoundException;
}
