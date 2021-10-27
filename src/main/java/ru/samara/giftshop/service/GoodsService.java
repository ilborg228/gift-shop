package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;

import java.util.List;

public interface GoodsService {

    void saveItem(ProductEntity product) throws ProductAlreadyExistException;

    List<ProductEntity> getAllItems();

    void deleteItem(Long id) throws NoSuchProductException;

    void updateItems(ProductEntity p) throws NoSuchProductException;
}
