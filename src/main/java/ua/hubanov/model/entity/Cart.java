package ua.hubanov.model.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Long id;
    private Set<Product> products;
    private Map<Product, Integer> productsWithQuantity = new HashMap<>();
    private Set<Order> orders;

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
    }

    public Cart(Long id, Set<Product> products, Map<Product, Integer> productsWithQuantity, Set<Order> orders) {
        this.id = id;
        this.products = products;
        this.productsWithQuantity = productsWithQuantity;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProductsWithQuantity() {
        return productsWithQuantity;
    }

    public void setProductsWithQuantity(Map<Product, Integer> productsWithQuantity) {
        this.productsWithQuantity = productsWithQuantity;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
             //   ", products=" + products +
              //  ", productsWithQuantity=" + productsWithQuantity +
              //  ", orders=" + orders +
                '}';
    }
}
