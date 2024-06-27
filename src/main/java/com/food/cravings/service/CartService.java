package com.food.cravings.service;

import com.food.cravings.model.Cart;
import com.food.cravings.model.CartItem;
import com.food.cravings.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception;

    public CartItem updatedCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long CartItemId,String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;
    public Cart findCartByCartId(Long cartId) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;

}
