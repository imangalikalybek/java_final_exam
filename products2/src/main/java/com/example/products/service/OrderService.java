package com.example.products.service;

import com.example.products.model.Order;
import com.example.products.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return (List<Order>) orderRepository.findAll();
    }
    public ResponseEntity<?> getById(long id) {
        return ResponseEntity.ok(orderRepository.findById(id));
    }


    public void save(long shop_id){
        LocalDate date = LocalDate.now();
        float total_price=0;
        orderRepository.insertorder(shop_id,total_price,date,1);
    }
    public ResponseEntity<?> getByShop_id(long id) {
        return ResponseEntity.ok(orderRepository.findByShopId(id));
    }



}

