package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;

import java.util.List;

public interface CategoryService {

    CategoryEntity saveNewItem(CategoryEntity category);

    List<CategoryEntity> findAll();

    void delete(Long id);

    void update(CategoryEntity p);

    CategoryEntity findById(Long id);

    List<ProductEntity> findByName(String categoryName);
}
