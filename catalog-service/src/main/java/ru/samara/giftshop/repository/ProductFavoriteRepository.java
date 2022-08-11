package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.ProductFavorite;

@Repository
public interface ProductFavoriteRepository extends JpaRepository<ProductFavorite, Long> {
}
