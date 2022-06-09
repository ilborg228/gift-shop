package ru.samara.giftshop.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.service.CommentService

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(private val commentService: CommentService) : BaseController() {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comments")
    fun addComment(@RequestBody comment: Comment) {
        return commentService.addComment(comment)
    }

    @DeleteMapping("/comments/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteComments(@PathVariable productId : Long) {
        commentService.deleteComment(productId)
    }

    @PatchMapping("/comments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun editComments(@RequestBody comment: Comment) {
        commentService.editComment(comment)
    }
}