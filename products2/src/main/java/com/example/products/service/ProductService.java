package com.example.products.service;

import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return (List<Product>) productRepository.findAll();
    }

    public  List<Product> findAllByCategory_id(long id){
        return  productRepository.findAllByCategory_id(id);
    }
}

