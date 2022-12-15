package ru.samara.giftshop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.entity.ProductImage;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    boolean existsByName(String name);

    List<Product> findProductsByCategoryIdAndProductImages(Long categoryId, List<ProductImage> productImages , Pageable pageable);
}
