package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CategoryDTO;
import ru.samara.giftshop.dto.DTOMapper;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.service.CategoryServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @GetMapping("/category")
    public List<CategoryDTO> getAllCategories(){
        List<CategoryDTO> l = categoryService.findAll()
                .stream()
                .map(DTOMapper::toCategoryDTO)
                .collect(Collectors.toList());
        return l;
    }

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category c = categoryService.saveNewItem(category);
        return ResponseEntity.status(201).body(c);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok("Товар успешно удалён");
    }

    @PatchMapping("/category")
    public ResponseEntity<?> updateCategory(@RequestBody Category entity){
        categoryService.update(entity);
        return ResponseEntity.ok("Категория успешно обновлена");
    }
}