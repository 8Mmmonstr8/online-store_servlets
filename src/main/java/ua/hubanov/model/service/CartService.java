package ua.hubanov.model.service;

import ua.hubanov.exceptions.AlreadyInCartException;
import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.exceptions.StockQuantityIsNotEnoughException;
import ua.hubanov.model.dao.CartDao;
import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.entity.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CartService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Cart findById(long id) throws CartNotFoundException {
        try (CartDao dao = daoFactory.createCartDao()) {
            return dao.findById(id)
                    .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + id));
        }
    }

    public Long createNewCart(Cart newCart) {
        try (CartDao dao = daoFactory.createCartDao()) {
            return dao.createNewCart(newCart);
        }
    }

    public Map<Product, Integer> getAllProductsInCart(User user) {
        try (CartDao dao = daoFactory.createCartDao()) {
            try {
                return dao.findAllProductsInCart(user.getCart());
            } catch (ProductNotFoundException | CartNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public BigDecimal getTotal(Map<Product, Integer> productsWithNeededQuantity) {
        return productsWithNeededQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public List<OrderedProduct> getAllApprovedOrderedProductsOfUser(Set<OrderedProduct> orderedProducts) {
        return orderedProducts.stream()
                .filter(x -> x.getOrder().isApproved())
                .sorted(Comparator.comparingLong(x -> x.getOrder().getId()))
                .collect(Collectors.toList());
    }

    public List<OrderedProduct> getAllNotApprovedOrderedProductsOfUser(Set<OrderedProduct> orderedProducts) {
        return orderedProducts.stream()
                .filter(x -> !x.getOrder().isApproved())
                .sorted(Comparator.comparingLong(x -> x.getOrder().getId()))
                .collect(Collectors.toList());
    }

    public Set<OrderedProduct> getAllOrderedProductsOfUser(User user) {
        try (CartDao dao = daoFactory.createCartDao()) {
            return dao.getAllOrderedProductsOfUser(user);
        } catch (ProductNotFoundException | CartNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addProductToCart(User user, Long productId) throws StockQuantityIsNotEnoughException, AlreadyInCartException {
        try (CartDao dao = daoFactory.createCartDao()) {
            dao.addProductToCartByCartIdAndProductId(user.getCart().getId(), productId);
        }
    }

    public void updateNeededQuantity(User user, Long productId, Integer neededQuantity) throws StockQuantityIsNotEnoughException {
        try (CartDao dao = daoFactory.createCartDao()) {
            dao.updateNeededQuantity(user.getCart().getId(), productId, neededQuantity);
        }
    }

    public void deleteProductFromCart(User user, Long productId) {
        try (CartDao dao = daoFactory.createCartDao()) {
            dao.deleteProductFromCart(user.getCart().getId(), productId);
        }
    }

    public void checkOut(User user) throws StockQuantityIsNotEnoughException {
        try (CartDao dao = daoFactory.createCartDao()) {
            dao.checkOut(user.getId(), user.getCart().getId());
        }
    }
}
