package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CategoryDto;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.helpers.HttpHeaders;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.helpers.OrderByType;
import ru.samara.giftshop.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(
            @RequestParam(required = false, name = PARENT_ID) Long parentId,
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE) Integer page,
            @RequestParam(required = false, name = PAGE_SIZE, defaultValue = DEF_PARAM_PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false, name = ORDER_BY) OrderBy orderBy,
            @RequestParam(required = false, name = ORDER_BY_TYPE, defaultValue = DEF_PARAM_ORDER_BY_TYPE) OrderByType orderByType) {
        return categoryService.findAll(parentId, page, pageSize, orderBy, orderByType);
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody CategoryDto category) throws Exception {
        return categoryService.addCategory(category);
    }

    @GetMapping(value = "/categories",
            headers = HttpHeaders.PRODUCE_VIEW + "=" + HttpHeaders.CATEGORY_BY_NAME)
    public CategoryDto getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name);
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