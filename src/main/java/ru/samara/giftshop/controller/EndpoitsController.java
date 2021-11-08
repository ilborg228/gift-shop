package ru.samara.giftshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/")
public class EndpoitsController {

    @GetMapping
    public Map<String,String> getAllEndpoints(){
        Map<String,String> l = new HashMap<>();
        l.put("/allgoods","GET");
        l.put("/allcategories","GET");
        l.put("/products/{categoryName}","GET");
        l.put("/categories/{id}","GET");
        l.put("/addproduct/{categoryId}","POST");
        l.put("/addcategory","POST");
        l.put("/deleteproduct/{id}","DELETE");
        l.put("/deletecategory/{id}","DELETE");
        l.put("/updateproduct","UPDATE");
        return l;
    }
}
