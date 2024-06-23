package com.project.shopybackend;

public class OrderProduct {
    private String productId;
    private int quantity;
    private String name;
    private String description;
    private String price;
    private String imageUrl;
    public OrderProduct(String productId, int quantity, String name, String description, String price, String imageUrl) {
        this.productId = productId;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public OrderProduct() {
    }

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
