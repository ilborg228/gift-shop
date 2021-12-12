package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.DTO.DTOMapper;
import ru.samara.giftshop.DTO.ProductDTO;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping("/addproduct/{categoryId}")
    public ResponseEntity<?> addProduct
            (@PathVariable Long categoryId,@RequestBody ProductEntity product){
        product.setCategory(categoryService.findById(categoryId));
        productService.saveNewItem(product);
        return ResponseEntity.status(201).body("Товар успешно добавлен");
    }

    @GetMapping("/allproducts")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> l = productService.findAll()
                .stream()
                .map(DTOMapper::toProductDTO)
                .collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(l));
    }

    @GetMapping("/products/{categoryName}")
    public ResponseEntity<?> getProductsByCategoryName(@PathVariable String categoryName){
        List<ProductDTO> l = categoryService.findByName(categoryName)
                .stream()
                .map(DTOMapper::toProductDTO)
                .collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(l));
    }

    @DeleteMapping("/deleteproduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        ProductEntity p = productService.delete(id);
        return ResponseEntity.ok(p);
    }

    @PutMapping("/updateproduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductEntity p){
        productService.update(p);
        return ResponseEntity.ok(p);
    }
}
