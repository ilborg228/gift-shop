package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.ProductDetails;
import ru.samara.giftshop.dto.ProductDto;
import ru.samara.giftshop.dto.ProductListDto;
import ru.samara.giftshop.helpers.HttpHeaders;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController extends BaseController{

    private final ProductService productService;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProduct(@RequestBody ProductDetails product){
        productService.saveNewItem(product);
    }

    @GetMapping(value = "/products",
            headers = HttpHeaders.PRODUCE_VIEW + "=" + HttpHeaders.PRODUCT_BY_NAME)
    public Long getProductId(@RequestParam String name) {
        return productService.getProductIdByName(name);
    }

    @GetMapping("/products/{id}")
    public ProductDetails getProductDetails(@PathVariable Long id){
        return productService.getProductDetails(id);
    }

    @GetMapping("/categories/{categoryId}/products")
    public ProductListDto getProducts(
            @PathVariable Long categoryId,
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false, name = ORDER_BY) OrderBy orderBy,
            @RequestParam(required = false, name = ORDER_BY_TYPE, defaultValue = DEF_PARAM_ORDER_BY_TYPE) OrderByType orderByType) {
        return productService.getByCategoryId(categoryId, page, pageSize, orderBy, orderByType);
    }

    @GetMapping("/products")
    public List<ProductDto> getMostViewed() {
        return productService.getMostViewed();
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

    @PatchMapping("/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody ProductDetails p){
        productService.updateInStock(p);
    }
}
