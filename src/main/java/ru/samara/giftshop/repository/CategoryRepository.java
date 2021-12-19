package ru.samara.giftshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.CategoryEntity;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {

    boolean existsByCategoryName(String name);

    Optional<CategoryEntity> findByCategoryName(String name);
}
