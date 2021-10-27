package ru.samara.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    @Autowired
    private ProductService service2;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.service = categoryService;
    }

    @GetMapping("/allcategories")
    public List<CategoryEntity> getAllCategories(){
        List<CategoryEntity> l= service.getAllItems();
        return service.getAllItems();
    }

    @PostMapping("/addcategory")
    public ResponseEntity addCategory(@RequestBody CategoryEntity category){
        try {
            category.getProducts().addAll(service2.getAllItems());
            service.addItem(category);
            return ResponseEntity.ok("Товар успешно добавлен");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/deletecategory")
    public ResponseEntity deleteCategory(@RequestBody CategoryEntity c){
        try {
            service.deleteItem(c.getId());
            return ResponseEntity.ok("Товар успешно удалён");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
