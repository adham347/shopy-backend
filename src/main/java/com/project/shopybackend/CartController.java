package com.project.shopybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public String addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return "Cart added successfully!";
    }

    @GetMapping("/{userId}")
    public Cart getCartByUserId(@PathVariable String userId){return cartService.getCartByUserId(userId);}

    @PutMapping("/{userId}")
    public void updateCart(@PathVariable String userId, @RequestBody Cart cart){cartService.editCart(userId,cart);}

    @PutMapping("/{userId}/addproduct")
    public void addProductToCart(@PathVariable String userId, @RequestBody OrderProduct orderProduct){
        cartService.addProductToCart(userId, orderProduct.getProductId(), orderProduct.getQuantity());
    }

    @PutMapping("/{userId}/checkout")
    public void checkOut(@PathVariable String userId){
        cartService.checkOut(userId);
    }

    @PutMapping("/{userId}/editproductquantity")
    public void editProductQuantity(@PathVariable String userId, @RequestBody OrderProduct orderProduct){
        cartService.editProductQuantity(userId, orderProduct.getProductId(), orderProduct.getQuantity());
    }

    @PutMapping("/{userId}/clearcart")
    public void clearCart(@PathVariable String userId){cartService.clearCart(userId);}

    @DeleteMapping("/{userId}")
    public void deleteCart(@PathVariable String userId){cartService.deleteCart(userId);}
}
