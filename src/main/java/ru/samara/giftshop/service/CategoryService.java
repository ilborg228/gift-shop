package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;
import ru.samara.giftshop.exceptions.NoSuchCategoryException;
import ru.samara.giftshop.exceptions.NoSuchProductException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void saveNewItem(CategoryEntity category) throws CategoryAlreadyExistException;

    List<CategoryEntity> findAll();

    void delete(Long id) throws NoSuchProductException;

    void update(CategoryEntity p) throws NoSuchProductException;

    CategoryEntity findById(Long id) throws NoSuchCategoryException;
}
