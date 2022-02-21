package ru.samara.giftshop.repository

import org.springframework.data.repository.CrudRepository
import ru.samara.giftshop.entity.Comment

interface CommentRepository : CrudRepository<Comment,Long>  {
}