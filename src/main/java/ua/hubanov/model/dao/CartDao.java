package ua.hubanov.model.dao;

import ua.hubanov.model.entity.Cart;

public interface CartDao extends GenericDao<Cart> {
    public Long createNewCart(Cart newCart);
}
