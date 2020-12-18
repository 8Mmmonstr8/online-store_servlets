package ua.hubanov.model.service;

import ua.hubanov.model.dao.CartDao;
import ua.hubanov.model.dao.DaoFactory;
import ua.hubanov.model.dao.UserDao;
import ua.hubanov.model.entity.Cart;
import ua.hubanov.model.entity.User;

public class CartService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Long createNewCart(Cart newCart) {
        try (CartDao dao = daoFactory.createCartDao()) {
            return dao.createNewCart(newCart);
        }
    }
}
