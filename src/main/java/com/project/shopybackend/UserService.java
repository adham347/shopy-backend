package com.project.shopybackend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class UserService {
    @Autowired
    private Firestore firestore;

    public void addUser(User user){
        DocumentReference docRef = firestore.collection("users").document(user.getId());
        ApiFuture<WriteResult> result = docRef.set(user);
        try {
            System.out.println("User added at: " +result.get().getUpdateTime());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        CollectionReference productsRef = firestore.collection("users");
        ApiFuture<QuerySnapshot> querySnapshot = productsRef.get();

        List<User> users = new ArrayList<>();
        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                users.add(document.toObject(User.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(String userId) {
        DocumentReference docRef = firestore.collection("users").document(userId);
        ApiFuture<DocumentSnapshot> document = docRef.get();
        try {
            if (document.get().exists()) {
                return document.get().toObject(User.class);
            } else {
                return null; // Product not found
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editUser(String userId, User updatedUser) {
        DocumentReference docRef = firestore.collection("users").document(userId);
        ApiFuture<WriteResult> result = docRef.set(updatedUser);
        try {
            System.out.println("User updated at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userId) {
        DocumentReference docRef = firestore.collection("users").document(userId);
        ApiFuture<WriteResult> result = docRef.delete();
        try {
            System.out.println("User deleted at: " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
