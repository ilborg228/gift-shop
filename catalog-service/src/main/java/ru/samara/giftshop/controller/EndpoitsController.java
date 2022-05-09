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
        l.put("/product","GET");
        l.put("/category","GET");
        l.put("/product/{categoryName}","GET");
        l.put("/category/{id}","GET");
        l.put("/product/{categoryId}","POST");
        l.put("/category","POST");
        l.put("/product/{id}","DELETE");
        l.put("/category/{id}","DELETE");
        l.put("/product","PATCH");
        return l;
    }
}
