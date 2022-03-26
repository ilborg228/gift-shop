package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
