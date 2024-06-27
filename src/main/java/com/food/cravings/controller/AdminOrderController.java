package com.food.cravings.controller;

import com.food.cravings.model.Order;
import com.food.cravings.model.User;
import com.food.cravings.service.OrderService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController
{
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String authorization
    ) throws Exception{
        User user =userService.findUserByJwtToken(authorization);
        List<Order> orders = orderService.getRestaurantsOrder(id,order_status);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization") String authorization) throws Exception{
//    User user =userService.findUserByJwtToken(authorization);
    Order orders = orderService.updateOrder(orderId, orderStatus);
    return new ResponseEntity<>(orders, HttpStatus.OK);
    }


}
