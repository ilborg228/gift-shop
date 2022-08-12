package ru.samara.giftshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CategoryDto;
import ru.samara.giftshop.dto.ProductDto;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE) Integer page,
            @RequestParam(required = false, name = PAGE_SIZE, defaultValue = DEF_PARAM_PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false, name = ORDER_BY) OrderBy orderBy,
            @RequestParam(required = false, name = ORDER_BY_TYPE, defaultValue = DEF_PARAM_ORDER_BY_TYPE) OrderByType orderByType) {
        return categoryService.findAll(page, pageSize, orderBy, orderByType);
    }

    @GetMapping("/categories/{categoryId}/products")
    public List<ProductDto> getProducts(
            @PathVariable Long categoryId,
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false, name = ORDER_BY) OrderBy orderBy,
            @RequestParam(required = false, name = ORDER_BY_TYPE, defaultValue = DEF_PARAM_ORDER_BY_TYPE) OrderByType orderByType) {
        return productService.getByCategoryId(categoryId,page, pageSize, orderBy, orderByType);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) throws JsonProcessingException {
        return categoryService.saveNewItem(category);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PatchMapping("/categories")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody Category entity) {
        categoryService.update(entity);
    }
}