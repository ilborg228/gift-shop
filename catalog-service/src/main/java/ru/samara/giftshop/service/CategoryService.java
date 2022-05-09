package ru.samara.giftshop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;

import java.util.List;

public interface CategoryService {

    Category saveNewItem(Category category) throws JsonProcessingException;

    List<Category> findAll();

    void delete(Long id);

    void update(Category p);

    List<Product> findByName(String categoryName);
}
