package ru.samara.giftshop.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.exceptions.ApiException
import ru.samara.giftshop.exceptions.DataValidationResponse
import ru.samara.giftshop.repository.CommentRepository

@Service
class CommentService (val commentRepository: CommentRepository,@Value("comment.length") val commentLength:Int){
    fun addComment(data: Comment) {
        if(data.text.isNotBlank() && data.text.length>3)
            commentRepository.save(data)
        else
            throw ApiException(DataValidationResponse.COMMENT_LENGTH_IS_NOT_CORRET,commentLength)
    }
}