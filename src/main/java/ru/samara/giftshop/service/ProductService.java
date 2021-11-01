package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;

import java.util.List;

public interface ProductService {

    void addItem(ProductEntity product);

    List<ProductEntity> getAllItems();

    void deleteItem(Long id);

    void updateItems(ProductEntity p);
}
