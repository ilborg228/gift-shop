package ru.samara.giftshop.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.entity.Product
import ru.samara.giftshop.service.CommentService

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(private val commentService: CommentService) {

    @PostMapping("/comment")
    fun addComment(text: String, productId : Long): ResponseEntity<Any>? {
        val data = Comment()
        data.text=text
        val product = Product()
        product.id=productId
        data.product = product
        return ResponseEntity.status(201).body(commentService.addComment(data))
    }

    @GetMapping("/comment/{productId}")
    fun getComment(@PathVariable productId : Long,
                   @RequestBody req:Comment): ResponseEntity<Any> {
        val data = Comment()
        data.text=req.text
        val product = Product()
        product.id=productId
        data.product = product
        return ResponseEntity.status(201).body(commentService.addComment(data))
    }
}