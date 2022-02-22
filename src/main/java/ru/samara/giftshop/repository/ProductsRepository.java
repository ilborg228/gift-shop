package ru.samara.giftshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Product;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
    boolean existsByName(String name);
}
