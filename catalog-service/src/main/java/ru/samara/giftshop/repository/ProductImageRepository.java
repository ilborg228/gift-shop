package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.samara.giftshop.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
