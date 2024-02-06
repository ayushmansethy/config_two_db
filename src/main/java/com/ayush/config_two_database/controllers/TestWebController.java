package com.ayush.config_two_database.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.config_two_database.product.Product;
import com.ayush.config_two_database.product.ProductRepo;
import com.ayush.config_two_database.users.Users;
import com.ayush.config_two_database.users.UsersRepo;

@RestController
@RequestMapping("/api")
public class TestWebController {
    @Autowired
    @Qualifier("productEntityManeger")
   ProductRepo productRepo ; 
    @Autowired
    @Qualifier("usersEntityManeger")
   UsersRepo usersRepo ;




    @GetMapping
    public String giveData(){
        try {
            Product product = new Product(165, "this is good");

            productRepo.save(product) ;


        Users users = new Users(4565, "ayushman sethy");

            usersRepo.save(users);
        return "every thing is good " ; 
        } catch (Exception e) {
            e.printStackTrace() ;
            return "nothing good" ;
        }
        
        
    }
    
}
