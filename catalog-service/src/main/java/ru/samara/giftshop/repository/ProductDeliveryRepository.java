package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.ProductDelivery;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery,Long> {
}
