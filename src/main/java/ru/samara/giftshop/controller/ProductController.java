package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.DTO.DTOMapper;
import ru.samara.giftshop.DTO.ProductDTO;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataNotFoundResponse;
import ru.samara.giftshop.repository.ProductsRepository;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;
    private final ProductsRepository productsRepository;
    private final CategoryService categoryService;

    @PostMapping("/product/{categoryId}")
    public ResponseEntity<?> addProduct
            (@PathVariable Long categoryId,@RequestBody Product product){
        product.setCategory(categoryService.findById(categoryId));
        productService.saveNewItem(product);
        return ResponseEntity.status(201).body("Товар успешно добавлен");
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> l = productService.findAll()
                .stream()
                .map(DTOMapper::toProductDTO)
                .collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(l));
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProductsByCategoryName(@PathVariable Long id){
        ProductDTO p = DTOMapper.toProductDTO(
                productsRepository.findById(id).orElseThrow(()-> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND)));
        return p;
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Product p = productService.delete(id);
        return ResponseEntity.ok(p);
    }

    @PatchMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody Product p){
        productService.update(p);
        return ResponseEntity.ok(p);
    }
}
