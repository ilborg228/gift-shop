package ru.samara.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.NoSuchCategoryException;
import ru.samara.giftshop.service.CategoryServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    private final CategoryServiceImpl service;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.service = categoryService;
    }

    @GetMapping("/allcategories")
    public ResponseEntity<?> getAllCategories(){
        List<CategoryEntity> l = service.findAll();
        return ResponseEntity.of(Optional.of(l));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable Long id) throws NoSuchCategoryException {
        try {
            return ResponseEntity.of(Optional.of(service.findById(id)));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/addcategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryEntity category){
        try {
            service.saveNewItem(category);
            return ResponseEntity.ok("Категория успешно добавлена");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/deletecategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Long id){
        try {
            service.delete(id);
            return ResponseEntity.ok("Товар успешно удалён");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
