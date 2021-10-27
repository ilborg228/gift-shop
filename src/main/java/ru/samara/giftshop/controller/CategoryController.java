package ru.samara.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.service.CategoryServiceImpl;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryServiceImpl service;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.service = categoryService;
    }

    @GetMapping("/allcategories")
    public List<CategoryEntity> getAllCategories(){
        List<CategoryEntity> l = service.getAllItems();
        return l;
    }

    @PostMapping("/addcategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryEntity category){
        try {
            service.addItem(category);
            return ResponseEntity.ok("Категория успешно добавлена");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/deletecategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Long id){
        try {
            service.deleteItem(id);
            return ResponseEntity.ok("Товар успешно удалён");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
