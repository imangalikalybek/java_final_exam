package com.example.products.service;

import com.example.products.model.Order;
import com.example.products.model.OrderItem;
import com.example.products.repository.OrderItemRepository;
import com.example.products.repository.OrderRepository;
import com.example.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;



    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }



    public void save(long shop_id, ArrayList<Integer> product_id, ArrayList<Integer> quantity, ArrayList<Float> price) {
        float total_price=0;
        for (int id : product_id) {
            int q = quantity.get(id);
            float p = price.get(id);
            total_price+=q*p;
            Order order = orderRepository.findByShopId(shop_id);
            orderItemRepository.insertorderitem(order.getId(), id, q, p);
            orderRepository.updateprice(total_price,order.getId());

        }

    }
    @Transactional
    public List<Order> getByShopID(long shop_id) {
        List<Order> list = orderRepository.findordersbyshop_id(shop_id);
        for(Order order:list){
            order.setOrderItems(orderItemRepository.getByorder_id(order.getId()));
        }
        return list;
    }
}