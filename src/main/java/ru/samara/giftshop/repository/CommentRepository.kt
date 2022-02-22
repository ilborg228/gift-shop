package ru.samara.giftshop.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.samara.giftshop.entity.Comment

@Repository
interface CommentRepository : CrudRepository<Comment,Long>  {
}