package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Product;

import java.util.List;

public interface CategoryService {

    Category saveNewItem(Category category);

    List<Category> findAll();

    void delete(Long id);

    void update(Category p);

    Category findById(Long id);

    List<Product> findByName(String categoryName);
}
