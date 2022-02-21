package ru.samara.giftshop.service;

import ru.samara.giftshop.entity.Product;

import java.util.List;

public interface ProductService {

    void saveNewItem(Product product);

    List<Product> findAll();

    Product delete(Long id);

    void update(Product p);
}
