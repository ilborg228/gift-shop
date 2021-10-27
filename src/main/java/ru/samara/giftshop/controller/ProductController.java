package ru.samara.giftshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryServiceImpl;
import ru.samara.giftshop.service.ProductService;

import java.util.List;

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
        try {
            product.setCategory(categoryService.getItemByID(categoryId));
            productService.addItem(product);
            return ResponseEntity.ok("Товар успешно добавлен");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/allgoods")
    public List<ProductEntity> getAllProducts(){
        return productService.getAllItems();
    }

    @DeleteMapping("/deleteproduct")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id){
        try {
            productService.deleteItem(id);
            return ResponseEntity.ok("Товар успешно удалён");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity p){
        try {
            productService.updateItems(p);
            return ResponseEntity.ok("Товар успешно обновлен");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
