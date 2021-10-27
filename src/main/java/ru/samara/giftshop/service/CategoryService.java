package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.exceptions.CategoryAlreadyExistException;
import ru.samara.giftshop.exceptions.NoSuchProductException;

import java.util.List;

public interface CategoryService {

    void addItem(CategoryEntity category) throws CategoryAlreadyExistException;

    List<CategoryEntity> getAllItems();

    void deleteItem(Long id) throws NoSuchProductException;

    void updateItems(CategoryEntity p) throws NoSuchProductException;

    CategoryEntity getItemByID(Long categoryId) throws NoSuchProductException;
}
