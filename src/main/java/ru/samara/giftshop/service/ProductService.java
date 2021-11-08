package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.ProductEntity;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    void saveNewItem(ProductEntity product);

    List<ProductEntity> findAll();

    void delete(Long id);

    void update(ProductEntity p);
}
