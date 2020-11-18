package com.example.products.repository;

import com.example.products.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into orderr(shop_id,total_price,date,status) values(:shop_id,:total_price,:date,:status)", nativeQuery = true )
    void insertorder(@Param("shop_id") long shop_id, @Param("total_price") float total_price, @Param("date") LocalDate date, @Param("status") long status);

    @Query(value = "Select * from orderr where shop_id=? ORDER BY id DESC LIMIT 1", nativeQuery = true )
    Order findByShopId(long shop_id);
    @Query(value = "Select * from orderr where shop_id=?", nativeQuery = true )
    List<Order> findordersbyshop_id(long shop_id);

    @Transactional
    @Modifying
    @Query(value = "Update orderr SET total_price=?1 where id=?2 ", nativeQuery = true )
    void updateprice(float total_price, long id);

}