package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Category;
import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    boolean existsByCategoryName(String name);

    Optional<Category> getByCategoryName(String name);
}
