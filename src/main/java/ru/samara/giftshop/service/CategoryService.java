package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;

import java.util.List;

public interface CategoryService {

    void saveNewItem(CategoryEntity category);

    List<CategoryEntity> findAll();

    void delete(Long id);

    void update(CategoryEntity p);

    CategoryEntity findById(Long id);
}
