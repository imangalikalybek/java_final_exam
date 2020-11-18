package com.example.products.controller;


import com.example.products.model.Order;
import com.example.products.model.OrderItem;
import com.example.products.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping(value="/orderItem/create")
    public void save(@RequestHeader("shop_id") long shop_id, @RequestHeader("product_id") ArrayList<Integer> product_id, @RequestHeader("quantity") ArrayList<Integer> quantity, @RequestHeader("price") ArrayList<Float> price){
        orderItemService.save( shop_id, product_id, quantity, price);
    }
    @GetMapping(value="/orderItem/auth")
    public ResponseEntity<?> getorderitem(@RequestHeader("shop_id") long shop_id){
        return ResponseEntity.ok(orderItemService.getByShopID(shop_id));}


}

