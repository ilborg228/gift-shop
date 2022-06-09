package ru.samara.giftshop.service

import org.springframework.stereotype.Service
import ru.samara.giftshop.dto.CommentDto
import ru.samara.giftshop.dto.DtoMapper
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.exceptions.ApiException
import ru.samara.giftshop.exceptions.DataNotFoundResponse
import ru.samara.giftshop.exceptions.DataValidationResponse
import ru.samara.giftshop.repository.CommentRepository
import java.util.*

@Service
class CommentService(private val commentRepository: CommentRepository) : BaseService() {

    private val commentLength: Int = 3

    fun addComment(data: CommentDto):Comment {
        if (data.text.isNotBlank() && data.text.length > 3) {
            data.creation = Date()
            return commentRepository.save(DtoMapper.toComment(data))
        } else
            throw ApiException(DataValidationResponse.COMMENT_LENGTH_IS_NOT_CORRET, commentLength)
    }

    fun deleteComment(productId: Long) {
        commentRepository.deleteById(productId)
    }

    fun editComment(id: Long, comment: Comment) {
        val op: Optional<Comment> = commentRepository.findById(id)
        if (op.isPresent) {
            val old = op.get()
            if (old.text != null && comment.text == null) {
                comment.text = old.text
            }
            commentRepository.save(comment)
        } else {
            throw ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND)
        }
    }
}