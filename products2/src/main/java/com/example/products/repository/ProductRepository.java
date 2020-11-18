package com.example.products.repository;

import com.example.products.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value="select * from product where category_id = ? ORDER BY id ASC",nativeQuery = true)
    List<Product> findAllByCategory_id(long id);
}
