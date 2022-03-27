package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.DTO.DTOMapper;
import ru.samara.giftshop.DTO.ProductDTO;
import ru.samara.giftshop.DTO.ProductDetails;
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
        productService.saveNewItem(product, categoryId);
        return ResponseEntity.status(201).body("Товар успешно добавлен");
    }

    @GetMapping("/products/{categoryId}")
    public List<ProductDTO> getAllProducts(@PathVariable Long categoryId){
        List<ProductDTO> l = productService.getByCategoryId(categoryId)
                .stream()
                .map(DTOMapper::toProductDTO)
                .collect(Collectors.toList());
        return l;
    }

    @GetMapping("/product/{id}")
    public ProductDetails getProductDetails(@PathVariable Long id){
        ProductDetails p = DTOMapper.toProductDetails(
                productsRepository.findById(id).orElseThrow(()-> new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND)));
        return p;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

    @PatchMapping("/product")
    public void updateProduct(@RequestBody Product p){
        productService.update(p);
    }
}
