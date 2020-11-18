package com.example.products.service;

import com.example.products.model.Login;
import com.example.products.repository.LoginRepository;
import com.example.products.repository.ShopRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final ShopRepository shopRepository;

    public LoginService(LoginRepository loginRepository, ShopRepository shopRepository) {
        this.loginRepository = loginRepository;
        this.shopRepository = shopRepository;
    }

    @Transactional
    public Login signIn(Login login) throws Exception {
        String log=login.getLogin();
        String password=login.getPassword();
        Login auth= loginRepository.findByLoginAndPassword(log,password);

        if(auth==null){
            throw new Exception();
        }
        else{
            String token= UUID.randomUUID().toString();
            auth.setToken(token);
            loginRepository.save(auth);
            return auth;
        }
    }
}

