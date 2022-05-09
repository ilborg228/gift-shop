package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.FavoriteProduct;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct,Long> {
}
