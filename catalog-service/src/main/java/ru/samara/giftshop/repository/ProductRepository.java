package ru.samara.giftshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Optional<Product> findByName(String name);

    List<Product> findProductsByCategoryId(Long categoryId, Pageable pageable);

    Long countAllByCategoryId(Long categoryId);

    @Modifying
    @Query("update Product set views = views + 1 where id = :productId")
    void incrementView(Long productId);
}
