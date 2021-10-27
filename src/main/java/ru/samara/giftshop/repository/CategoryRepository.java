package ru.samara.giftshop.repository;

import org.springframework.data.repository.CrudRepository;
import ru.samara.giftshop.entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {

    boolean existsByCategoryName(String name);
}
