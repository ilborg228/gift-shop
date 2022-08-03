package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CommentDto;
import ru.samara.giftshop.dto.ProductDetails;
import ru.samara.giftshop.entity.Comment;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends BaseController{

    private final ProductService productService;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody ProductDetails product){
        return productService.saveNewItem(product);
    }

    @GetMapping("/products/{id}")
    public ProductDetails getProductDetails(@PathVariable Long id){
        return productService.getProductDetails(id);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

    @PatchMapping("/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody Product p){
        productService.update(p);
    }
}
