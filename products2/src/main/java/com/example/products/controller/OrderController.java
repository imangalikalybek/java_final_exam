package com.example.products.controller;

import com.example.products.model.Order;
import com.example.products.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value="/order")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.getAll());

    }
    @PostMapping(value="/ordder")
    public void save(@RequestHeader("shop_id") long shop_id){
        orderService.save(shop_id);
    }

}
