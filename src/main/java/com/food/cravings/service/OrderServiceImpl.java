package com.food.cravings.service;

import com.food.cravings.model.*;
import com.food.cravings.repository.*;
import com.food.cravings.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;
    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);
        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart= cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItems: cart.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItems.getFood());
            orderItem.setQuantity(cartItems.getQuantity());
            orderItem.setTotalPrice(cartItems.getTotalPrice());
            orderItem.setIngredients(cartItems.getIngredients());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        Long totalPrice = cartService.calculateCartTotal(cart);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        orderRepository.save(createdOrder);

        restaurant.getOrders().add(createdOrder);

//        restaurantRepository.save(restaurant);
        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                ||orderStatus.equals("PENDING")
                ||orderStatus.equals("COMPLETED")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        else{
            throw new Exception("Invalid order status: " + orderStatus);
        }

    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

        orderRepository.deleteById(orderId);

    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {

        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders = orders.stream().filter(
                    order -> order.getOrderStatus().equals(orderStatus)
            ).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new Exception("Order with id " + orderId + "Not found");
        }
        return order.get();
    }
}
