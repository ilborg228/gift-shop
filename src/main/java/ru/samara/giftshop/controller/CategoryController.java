package ru.samara.giftshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.DTO.CategoryDTO;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.NoSuchCategoryException;
import ru.samara.giftshop.service.CategoryServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryServiceImpl service;

    @GetMapping("/allcategories")
    public ResponseEntity<?> getAllCategories(){
        List<CategoryDTO> l = service.findAll()
                .stream()
                .map(CategoryDTO::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.of(Optional.of(l));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable Long id) throws NoSuchCategoryException {
        Optional<?> op = Optional.of(CategoryDTO.toDTO(service.findById(id)));
        return ResponseEntity.of(op);
    }

    @PostMapping("/addcategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryEntity category){
        CategoryEntity c = service.saveNewItem(category);
        return ResponseEntity.status(201).body(c);
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Товар успешно удалён");
    }

    @PutMapping("/updatecategory")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryEntity entity){
        service.update(entity);
        return ResponseEntity.ok("Категория успешно обновлена");
    }
}