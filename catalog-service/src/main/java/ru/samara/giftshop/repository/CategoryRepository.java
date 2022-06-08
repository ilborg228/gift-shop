package ru.samara.giftshop.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Category;
import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    boolean existsByCategoryName(String name);

    Optional<Category> getByCategoryName(String name);

    //TODO
    List<Category> getAll(Integer offset, Integer limit, Sort sort);
}
