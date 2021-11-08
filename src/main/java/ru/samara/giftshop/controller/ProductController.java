package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.DTO.CategoryDTO;
import ru.samara.giftshop.DTO.ProductDTO;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryServiceImpl;
import ru.samara.giftshop.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryServiceImpl categoryService;

    @PostMapping("/addproduct/{categoryId}")
    public ResponseEntity<?> addProduct(@RequestBody ProductEntity product, @PathVariable Long categoryId){
        product.setCategory(categoryService.findById(categoryId));
        productService.saveNewItem(product);
        return ResponseEntity.ok("Товар успешно добавлен");
    }

    @GetMapping("/allproducts")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> l = productService.findAll()
                .stream()
                .map(ProductDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(l));
    }

    @GetMapping("/products/{categoryName}")
    public ResponseEntity<?> getProductByName(@PathVariable String categoryName){
        List<ProductDTO> l = categoryService.findByName(categoryName)
                .stream()
                .map(ProductDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(l));
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok("Товар успешно удалён");
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity p){
        productService.update(p);
        return ResponseEntity.ok("Товар успешно обновлен");
    }
}
