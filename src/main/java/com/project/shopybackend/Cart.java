package com.project.shopybackend;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@Entity
public class Cart {


    private String UserId;
    private List<OrderProduct> orderProducts;
    private int totalPrice;


    public Cart(){}

    public Cart(String userId, List<OrderProduct> orderProducts) {
        this.UserId = userId;
        this.orderProducts = orderProducts;
    }

    public void addProduct(String productId, int quantity, String name, String description, String price, String imageUrl){
        OrderProduct orderProduct = new OrderProduct(productId,quantity,name,description,price,imageUrl);
        orderProducts.add(orderProduct);

    }
    public boolean editQuantity(String productId, int quantity){
        for (OrderProduct orderProduct : orderProducts){
            if(orderProduct.getProductId().equals(productId)){
                orderProduct.setQuantity(quantity);
                System.out.println("product "+productId+"quantity changed to "+quantity);

                return true;
            }
        }
        return false;
    }


    public void emptyCart(){
        orderProducts.clear();
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
