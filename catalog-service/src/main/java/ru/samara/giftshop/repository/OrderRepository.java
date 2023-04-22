package ru.samara.giftshop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByUserIdAndStatus(Long userId, Order.Status status);

    Optional<Order> findOrderByIdAndStatus(Long id, Order.Status status);

    @Modifying
    @Query("update Order set status = :status where id = :orderId")
    void updateStatus(Long orderId, Order.Status status);

    List<Order> findAllByStatusIsNot(Order.Status status, Pageable pageable);

    Long countAllByStatusIsNot(Order.Status status);
}
