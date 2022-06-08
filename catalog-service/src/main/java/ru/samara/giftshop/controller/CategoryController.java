package ru.samara.giftshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CategoryDTO;
import ru.samara.giftshop.dto.DTOMapper;
import ru.samara.giftshop.dto.ProductDTO;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.service.CategoryService;
import ru.samara.giftshop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController extends BaseController{

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories(
            @RequestParam(required = false, defaultValue = DEF_PARAM_OFFSET) Integer offset,
            @RequestParam(required = false, defaultValue = DEF_PARAM_LIMIT) Integer limit,
            @RequestParam(required = false, name = ORDER_BY) OrderBy orderBy,
            @RequestParam(required = false, name = ORDER_BY_TYPE, defaultValue = DEF_PARAM_ORDER_BY_TYPE) OrderByType orderByType){
        return categoryService.findAll(offset,limit,orderBy,orderByType);
    }

    @GetMapping("/categories/{categoryId}/products")
    public List<ProductDTO> getAllProducts(@PathVariable Long categoryId){
        return productService.getByCategoryId(categoryId)
                .stream()
                .map(DTOMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) throws JsonProcessingException {
        return categoryService.saveNewItem(category);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
    }

    @PatchMapping("/categories")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody Category entity){
        categoryService.update(entity);
    }
}