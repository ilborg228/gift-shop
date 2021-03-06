package ru.samara.giftshop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Category;
import java.util.*;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {

    boolean existsByCategoryName(String name);

    Optional<Category> findByCategoryName(String name);
}
