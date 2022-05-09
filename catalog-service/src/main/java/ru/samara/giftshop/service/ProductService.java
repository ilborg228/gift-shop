package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.Product;

import java.util.List;

public interface ProductService {

    void saveNewItem(Product product, Long categoryId);

    List<Product> getByCategoryId(Long categoryId);

    Product delete(Long id);

    void update(Product p);
}
