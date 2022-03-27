package ru.samara.giftshop.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.entity.Product
import ru.samara.giftshop.service.CommentService

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(private val commentService: CommentService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comment")
    fun addComment(@RequestBody comment: Comment) {
        return commentService.addComment(comment)
    }

    @GetMapping("/comment/{productId}")
    fun getComments(@PathVariable productId : Long): MutableIterable<Comment>? {
        return commentService.getComments(productId)
    }
}