package com.food.cravings.controller;

import com.food.cravings.model.CartItem;
import com.food.cravings.model.Order;
import com.food.cravings.model.User;
import com.food.cravings.request.AddCartItemRequest;
import com.food.cravings.request.OrderRequest;
import com.food.cravings.service.OrderService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request,
                                             @RequestHeader("Authorization") String authorization) throws Exception {
        User user = userService.findUserByJwtToken(authorization);
        Order order = orderService.createOrder(request,user);

        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                             @RequestHeader("Authorization") String authorization) throws Exception {
        User user = userService.findUserByJwtToken(authorization);
        List<Order> order = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }


}
