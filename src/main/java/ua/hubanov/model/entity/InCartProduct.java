package ua.hubanov.model.entity;

import javax.persistence.*;

public class InCartProduct {
    private Long id;
    private Product product;
    private Integer neededQuantity;
    private Cart cart;

    public InCartProduct() {
    }

    public InCartProduct(Long id, Product product, Integer neededQuantity, Cart cart) {
        this.id = id;
        this.product = product;
        this.neededQuantity = neededQuantity;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNeededQuantity() {
        return neededQuantity;
    }

    public void setNeededQuantity(Integer neededQuantity) {
        this.neededQuantity = neededQuantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "InCartProduct{" +
                "id=" + id +
           //     ", product=" + product +
                ", neededQuantity=" + neededQuantity +
           //     ", cart=" + cart +
                '}';
    }
}
