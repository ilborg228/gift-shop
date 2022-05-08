package ru.samara.giftshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Iterable<Comment> getAllByProductId(Long productId);
}