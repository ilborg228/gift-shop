package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.DTOMapper;
import ru.samara.giftshop.dto.ProductDTO;
import ru.samara.giftshop.dto.ProductDetails;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataNotFoundResponse;
import ru.samara.giftshop.repository.ProductRepository;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends BaseController{

    private final ProductService productService;

    @PostMapping("/products/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct
            (@PathVariable Long categoryId,@RequestBody Product product){
        return productService.saveNewItem(product, categoryId);
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
