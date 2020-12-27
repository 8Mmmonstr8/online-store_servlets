package ua.hubanov.model.service;

import ua.hubanov.exceptions.CartNotFoundException;
import ua.hubanov.exceptions.ProductNotFoundException;
import ua.hubanov.model.dao.CartDao;
import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.entity.*;

import java.math.BigDecimal;
import java.sql.SQLException;
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
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            } catch (CartNotFoundException e) {
                e.printStackTrace();
            }

//                    .stream()
//                    .collect(Collectors.toMap(inCartProduct -> inCartProduct.getProduct(),
//                            productQuantity -> productQuantity.getNeededQuantity()));
        }
        return null;
    }

    public BigDecimal getTotal(Map<Product, Integer> productsWithNeededQuantity) {
        return productsWithNeededQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Set<OrderedProduct> getAllApprovedOrderedProductsOfUser(Set<OrderedProduct> orderedProducts) {
        return orderedProducts.stream().filter(x -> x.getOrder().isApproved()).collect(Collectors.toSet());
    }

    public Set<OrderedProduct> getAllNotApprovedOrderedProductsOfUser(Set<OrderedProduct> orderedProducts) {
        return orderedProducts.stream().filter(x -> !x.getOrder().isApproved()).collect(Collectors.toSet());
    }

    public Set<OrderedProduct> getAllOrderedProductsOfUser(User user) {
        try (CartDao dao = daoFactory.createCartDao()) {
            return dao.getAllOrderedProductsOfUser(user);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        } catch (CartNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
