package ru.samara.giftshop.service

import org.springframework.stereotype.Service
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.exceptions.ApiException
import ru.samara.giftshop.exceptions.DataValidationResponse
import ru.samara.giftshop.repository.CommentRepository

@Service
class CommentService (private val commentRepository: CommentRepository) : BaseService(){

    private val commentLength: Int = 3

    fun addComment(data: Comment) {
        if(data.text.isNotBlank() && data.text.length>3)
            commentRepository.save(data)
        else
            throw ApiException(DataValidationResponse.COMMENT_LENGTH_IS_NOT_CORRET,commentLength)
    }

    fun getComments(productId: Long): MutableIterable<Comment>? {
        return commentRepository.findAllByProductId(productId);
    }
}