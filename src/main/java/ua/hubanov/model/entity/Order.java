package ua.hubanov.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

public class Order {
    private Long id;
    private User user;
    private Date orderDate;
    private Set<OrderedProduct> orderedProducts;
    private boolean isApproved;

    public Order() {
    }

    public Order(Long id, User user, Date orderDate, Set<OrderedProduct> orderedProducts, boolean isApproved) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.orderedProducts = orderedProducts;
        this.isApproved = isApproved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
