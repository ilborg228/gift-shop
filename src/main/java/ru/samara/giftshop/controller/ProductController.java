package ru.samara.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryServiceImpl;
import ru.samara.giftshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;
    private final CategoryServiceImpl categoryService;

    @Autowired
    public ProductController(ProductService service, CategoryServiceImpl categoryService) {
        this.productService = service;
        this.categoryService = categoryService;
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductEntity product, @RequestParam Long categoryId){
        product.setCategory(categoryService.findById(categoryId));
        productService.addItem(product);
        return ResponseEntity.ok("Товар успешно добавлен");
    }

    @GetMapping("/allproducts")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.of(Optional.of(productService.getAllItems()));
    }

    @DeleteMapping("/deleteproduct")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id){
        productService.deleteItem(id);
        return ResponseEntity.ok("Товар успешно удалён");
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity p){
        productService.updateItems(p);
        return ResponseEntity.ok("Товар успешно обновлен");
    }
}
