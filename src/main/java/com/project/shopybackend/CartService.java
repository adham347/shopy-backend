package com.project.shopybackend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CartService {
    @Autowired
    private Firestore firestore;

    public void addCart(Cart cart){
        //the cart doc id is set to be its user's id to easily get the users cars as it is a one-to-one relationship
        DocumentReference docRef = firestore.collection("carts").document(cart.getUserId());
        ApiFuture<WriteResult> result = docRef.set(cart); //add the product to firestore

        try {
            System.out.println("cart added at: " +result.get().getUpdateTime());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public Cart getCartByUserId(String userId){
        DocumentReference docRef = firestore.collection("carts").document(userId);
        ApiFuture<DocumentSnapshot> document = docRef.get();
        try {
            if (document.get().exists()) {
                return document.get().toObject(Cart.class);
            } else {
                System.out.println("cart doc not found");
                return null; // Product not found
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editCart(String userId, Cart updatedCart){
        DocumentReference docRef = firestore.collection("carts").document(userId);
        ApiFuture<WriteResult> result = docRef.set(updatedCart);
        try {
            System.out.println("Order updated at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void addProductToCart(String userId, String productId, int quantity){
        DocumentReference docRef = firestore.collection("carts").document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Update the order document with the new product
                Cart cart = document.toObject(Cart.class);
                if (cart != null) {
                    cart.addProduct(productId, quantity); // Assuming addProduct method exists in Order class
                    ApiFuture<WriteResult> writeResult = docRef.set(cart);
                    writeResult.get(); // Wait for the write to complete
                }
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public void editProductQuantity(String userId, String productId, int quantity){
        DocumentReference docRef = firestore.collection("carts").document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Update the order document with the new product
                Cart cart = document.toObject(Cart.class);
                if (cart != null) {
                    cart.editQuantity(productId, quantity); // Assuming addProduct method exists in Order class
                    ApiFuture<WriteResult> writeResult = docRef.set(cart);
                    writeResult.get(); // Wait for the write to complete
                }
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }
    public void clearCart(String userId){
        DocumentReference docRef = firestore.collection("carts").document(userId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                // Update the order document with the new product
                Cart cart = document.toObject(Cart.class);
                if (cart != null) {
                    cart.emptyCart(); // Assuming addProduct method exists in Order class
                    ApiFuture<WriteResult> writeResult = docRef.set(cart);
                    writeResult.get(); // Wait for the write to complete
                    System.out.println("cart cleared!");
                }
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    public void deleteCart(String userId){
        DocumentReference docRef = firestore.collection("carts").document(userId);
        ApiFuture<WriteResult> result = docRef.delete();
        try {
            System.out.println("cart deleted at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
