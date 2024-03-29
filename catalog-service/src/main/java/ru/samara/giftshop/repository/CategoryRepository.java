package ru.samara.giftshop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Category;

import java.util.*;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, JpaRepository<Category, Long> {

    boolean existsByCategoryName(String name);

    Optional<Category> findByCategoryName(String name);

    List<Category> findAllByParentId(Long parentId, Pageable pageable);

    @Modifying
    @Query("update Category set hasChild = true where id = :parentId")
    void updateParentCategory(Long parentId);
}
