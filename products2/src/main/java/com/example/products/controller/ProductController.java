package com.example.products.controller;
import com.example.products.model.Product;

import com.example.products.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value="/product",method= RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.getAll());

    }
    @RequestMapping(value="/product/cate/{id}",method=RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<?> getcate(@PathVariable long id){
        return ResponseEntity.ok(productService.findAllByCategory_id(id));
    }

}

