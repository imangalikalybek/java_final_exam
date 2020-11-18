package com.example.products.service;

import com.example.products.model.Category;
import com.example.products.repository.CategoryRepository;
import com.example.products.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){

        return (List<Category>)categoryRepository.findAll();
    }


}
