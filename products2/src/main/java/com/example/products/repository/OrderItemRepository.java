package com.example.products.repository;


import com.example.products.model.OrderItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into order_item (order_id,product_id,quantity,price) values(:order_id,:product_id,:quantity,:price)", nativeQuery = true )
    void insertorderitem(@Param("order_id") long order_id,@Param("product_id") long product_id,@Param("quantity") int quantity, @Param("price") float price);
    @Query(value = "select * from order_item where order_id=?", nativeQuery = true )
    List<OrderItem> getByorder_id(long id);


}