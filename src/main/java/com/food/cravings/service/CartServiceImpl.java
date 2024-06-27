package com.food.cravings.service;

import com.food.cravings.model.Cart;
import com.food.cravings.model.CartItem;
import com.food.cravings.model.Food;
import com.food.cravings.model.User;
import com.food.cravings.repository.CartItemRepository;
import com.food.cravings.repository.CartRepository;
import com.food.cravings.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(request.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem item : cart.getCartItems()){
            if(item.getFood().equals(food)){
                int newQuantity= item.getQuantity()+ request.getQuantity();
                return updatedCartItemQuantity(item.getId(),newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity()*food.getPrice());
        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updatedCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem =cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new Exception("Cart Item not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()) {
            throw new Exception("Cart item with id " + cartItemId + "Not Found");
        }

        CartItem items = cartItem.get();
        cart.getCartItems().remove(items);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItem item : cart.getCartItems()) {
            total += item.getFood().getPrice()* item.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartByCartId(Long cartId) throws Exception {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isEmpty()){
            throw new Exception("cart Not Found ");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
