package ru.samara.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.GoodsService;

import java.util.List;

@RestController
public class GoodsController {

    private GoodsService service;

    @Autowired
    public GoodsController(GoodsService service) {
        this.service = service;
    }

    @PostMapping("/addproduct")
    public ResponseEntity addProduct(@RequestBody ProductEntity product){
        try {
            service.saveProduct(product);
            return ResponseEntity.ok("Товар успешно добавлен");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/allgoods")
    public List<ProductEntity> getAllProducts(){
        return service.selectAllGoods();
    }
}
