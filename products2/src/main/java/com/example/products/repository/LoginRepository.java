package com.example.products.repository;

import com.example.products.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
    Login findByLoginAndPassword(String log,String password);
    Login findByToken(String token);
}