package ru.samara.giftshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.samara.giftshop.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long>  {
    Iterable<Comment> findAllByProductId(Long productId);
}