package ru.samara.giftshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class EndpoitsController {
    @RequestMapping("/endpoints")
    public Map<String,String> getAllEndpoints(){
        Map<String,String> l = new HashMap<>();
        l.put("/allgoods","GET");
        l.put("/addproduct","POST");

        return l;
    }
}
