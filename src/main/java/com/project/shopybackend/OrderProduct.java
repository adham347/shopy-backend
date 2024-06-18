package com.project.shopybackend;

public class OrderProduct {
    private String productId;
    private int quantity;
    public OrderProduct(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderProduct() {
    }

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
