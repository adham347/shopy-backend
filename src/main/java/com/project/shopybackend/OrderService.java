package com.project.shopybackend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrderService {
    @Autowired
    private Firestore firestore;

    public void addOrder(Order order){
        DocumentReference docRef = firestore.collection("orders").document();
        order.setId(docRef.getId());//set the product id
        order.setDate(LocalDate.now().toString());
        order.setStatus("preparing");
        ApiFuture<WriteResult> result = docRef.set(order); //add the product to firestore

        try {
            System.out.println("Order added at: " +result.get().getUpdateTime());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders(){
        CollectionReference ordersRef = firestore.collection("Orders");
        ApiFuture<QuerySnapshot> querySnapshot = ordersRef.get();

        List<Order> orders = new ArrayList<>();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                orders.add(document.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orders = new ArrayList<>();
        try {
            // Create a query to find orders with matching userId
            Query query = firestore.collection("orders").whereEqualTo("userId", userId);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            // Retrieve documents
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                orders.add(document.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrdersHistory(String userId){
        List<Order> orders = new ArrayList<>();
        try {
            // Create a query to find orders with matching userId
            Query query = firestore.collection("orders").whereEqualTo("userId", userId)
                    .whereEqualTo("status", "delivered");
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            // Retrieve documents
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                orders.add(document.toObject(Order.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public Order getOderById(String orderId){
        DocumentReference docRef = firestore.collection("orders").document(orderId);
        ApiFuture<DocumentSnapshot> document = docRef.get();
        try {
            if (document.get().exists()) {
                return document.get().toObject(Order.class);
            } else {
                return null; // Product not found
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editOrder(String orderId, Order updatedOrder){
        DocumentReference docRef = firestore.collection("orders").document(orderId);
        ApiFuture<WriteResult> result = docRef.set(updatedOrder);
        try {
            System.out.println("Order updated at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void addProductToOrder(String orderId, String productId, int quantity){
        DocumentReference docRef = firestore.collection("orders").document(orderId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Update the order document with the new product
                Order order = document.toObject(Order.class);
                if (order != null) {
                    order.addProduct(productId, quantity); // Assuming addProduct method exists in Order class
                    ApiFuture<WriteResult> writeResult = docRef.set(order);
                    writeResult.get(); // Wait for the write to complete
                }
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }


    public void deleteOrder(String orderId){
        DocumentReference docRef = firestore.collection("orders").document(orderId);
        ApiFuture<WriteResult> result = docRef.delete();
        try {
            System.out.println("Order deleted at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
