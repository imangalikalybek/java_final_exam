package com.example.products.controller;

import com.example.products.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping(value="/customers/me")
    public ResponseEntity<?> getMe(@RequestHeader("auth") String token) {
        return ResponseEntity.ok(shopService.getCustomerByToken(token));
    }

}
