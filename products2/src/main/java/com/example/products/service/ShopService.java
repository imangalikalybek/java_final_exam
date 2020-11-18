package com.example.products.service;

import com.example.products.model.Login;
import com.example.products.model.Shop;
import com.example.products.repository.LoginRepository;
import com.example.products.repository.ShopRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final LoginRepository loginRepository;

    public ShopService(ShopRepository shopRepository, LoginRepository loginRepository) {
        this.shopRepository = shopRepository;
        this.loginRepository = loginRepository;
    }

    public Shop getCustomerByToken(String token) {
        Login login = loginRepository.findByToken(token);
        return shopRepository.findById(login.getShop_id());

    }

}