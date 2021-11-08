package ru.samara.giftshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.samara.giftshop.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {

    boolean existsByCategoryName(String name);

    Optional<CategoryEntity> findByCategoryName(String name);
}
