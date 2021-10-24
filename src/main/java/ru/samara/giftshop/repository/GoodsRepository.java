package ru.samara.giftshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.ProductEntity;

@Repository
public interface GoodsRepository extends CrudRepository<ProductEntity, Long> {
    boolean existsByName(String name);
}
