package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Order;

@Repository
public interface ProductDeliveryRepository extends JpaRepository<Order,Long> {
}
