package com.project.shopybackend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    @Autowired
    private Firestore firestore;

    public void addProduct(Product product){
        DocumentReference docRef = firestore.collection("products").document();
        product.setId(docRef.getId()); //set the product id
        ApiFuture<WriteResult> result = docRef.set(product); //add the product to firestore

        try {
            System.out.println("Product added at: " +result.get().getUpdateTime());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts() {
        CollectionReference productsRef = firestore.collection("products");
        ApiFuture<QuerySnapshot> querySnapshot = productsRef.get();

        List<Product> products = new ArrayList<>();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                products.add(document.toObject(Product.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(String productId) {
        DocumentReference docRef = firestore.collection("products").document(productId);
        ApiFuture<DocumentSnapshot> document = docRef.get();
        try {
            if (document.get().exists()) {
                return document.get().toObject(Product.class);
            } else {
                return null; // Product not found
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editProduct(String productId, Product updatedProduct) {
        DocumentReference docRef = firestore.collection("products").document(productId);
        ApiFuture<WriteResult> result = docRef.set(updatedProduct);
        try {
            System.out.println("Product updated at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String productId) {
        DocumentReference docRef = firestore.collection("products").document(productId);
        ApiFuture<WriteResult> result = docRef.delete();
        try {
            System.out.println("Product deleted at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
