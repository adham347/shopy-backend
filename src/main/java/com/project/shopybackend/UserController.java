package com.project.shopybackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // add product
    @PostMapping
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "User added successfully";
    }

    // Get all products
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get product by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    // Update an existing product
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User user) {
        userService.editUser(userId,user);
    }

    // Delete a product
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
