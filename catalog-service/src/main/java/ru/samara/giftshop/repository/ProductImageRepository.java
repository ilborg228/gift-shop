package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.entity.ProductImage;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
