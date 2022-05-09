package ru.samara.giftshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CategoryDTO;
import ru.samara.giftshop.dto.DTOMapper;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<CategoryDTO> getAllCategories(){
        return categoryService.findAll()
                .stream()
                .map(DTOMapper::toCategoryDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) throws JsonProcessingException {
        return categoryService.saveNewItem(category);
    }

    @DeleteMapping("/category/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
    }

    @PatchMapping("/category")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody Category entity){
        categoryService.update(entity);
    }
}