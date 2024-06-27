package com.food.cravings.controller;

import com.food.cravings.model.Cart;
import com.food.cravings.model.CartItem;
import com.food.cravings.model.User;
import com.food.cravings.repository.UserRepository;
import com.food.cravings.request.AddCartItemRequest;
import com.food.cravings.request.UpdateCartItemRequest;
import com.food.cravings.service.CartService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest item, @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem= cartService.addItemToCart(item,jwt);
        return new ResponseEntity<>(cartItem,HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody UpdateCartItemRequest item,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem= cartService.updatedCartItemQuantity(item.getCartItemId(),item.getQuantity());
        return new ResponseEntity<>(cartItem,
                HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable("id") Long id,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart,
                HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart,
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart,
                HttpStatus.ACCEPTED);
    }

}
