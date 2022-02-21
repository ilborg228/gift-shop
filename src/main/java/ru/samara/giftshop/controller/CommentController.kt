package ru.samara.giftshop.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.entity.Product
import ru.samara.giftshop.service.CommentService

@RestController
class CommentController(private val commentService: CommentService) {

    @PostMapping("/comment")
    fun addComment(text: String, productId : Long): ResponseEntity<Any>? {
        val data = Comment()
        data.text=text
        val product = Product()
        product.id=productId
        data.product= product
        return ResponseEntity.status(201).body(commentService.addComment(data))
    }
}