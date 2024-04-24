package com.project.shopybackend;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {
    private String userId;
    private String id;
    private LocalDate date;
    private List<OrderProduct> orderProducts;

    public Order(){}

    public Order(String id, List<OrderProduct> orderProducts, String userId) {
        this.date = LocalDate.now();
        this.userId = userId;
        this.id = id;
        this.orderProducts = orderProducts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void addProduct(String productId, int quantity){
        OrderProduct orderProduct = new OrderProduct(productId,quantity);
        orderProducts.add(orderProduct);
    }
}
