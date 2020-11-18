package com.example.products.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long shop_id;
    private float total_price;
    private Date date;
    private long status;
    @Transient
    private List<OrderItem> orderItems;
    @Transient
    private List<Optional<Product>> products;
    @Transient
    private List<Shop> shop;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Shop> getShop() {
        return shop;
    }

    public void setShop(List<Shop> shop) {
        this.shop = shop;
    }

    public List<Optional<Product>> getProducts() {
        return products;
    }

    public void setProducts(List<Optional<Product>> products) {
        this.products = products;
    }
}
