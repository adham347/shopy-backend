package com.project.shopybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // add order
    @PostMapping
    public String addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return "Order added successfully";
    }

    //add product to order
    @PostMapping("/orders/{orderId}/products")
    public ResponseEntity<String> addProductToOrder ( @PathVariable String orderId, @RequestBody OrderProduct orderProduct){
        orderService.addProductToOrder(orderId, orderProduct.getProductId(), orderProduct.getQuantity());
        return ResponseEntity.ok("Product added to order successfully");
    }
    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get order by ID
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable String orderId) {
        return orderService.getOderById(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable String userId){return orderService.getOrdersByUserId(userId);}

    // Update an existing order
    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable String orderId, @RequestBody Order order) {
        orderService.editOrder(orderId, order);
    }

    // Delete a order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
    }
}
