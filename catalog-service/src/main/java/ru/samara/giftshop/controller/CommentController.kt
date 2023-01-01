package ru.samara.giftshop.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.samara.giftshop.dto.CommentDto
import ru.samara.giftshop.dto.CommentsSummary
import ru.samara.giftshop.entity.Comment
import ru.samara.giftshop.helpers.HttpHeaders
import ru.samara.giftshop.service.CommentService
import ru.samara.giftshop.service.ProductService

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class CommentController(private val commentService: CommentService,
                        private val productService: ProductService) : BaseController() {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comments")
    fun addComment(@RequestBody comment: CommentDto): CommentDto {
        return commentService.addComment(comment)
    }

    @PatchMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun editComment(@PathVariable id: Long, @RequestBody comment: Comment) {
        commentService.editComment(id, comment)
    }

    @GetMapping("/products/{productId}/comments")
    fun getCommentsByProduct(@PathVariable productId: Long): List<CommentDto?> {
        return productService.getCommentsByProductId(productId)
    }

    @GetMapping("/products/{productId}/comments", headers = [HttpHeaders.PRODUCE_VIEW + "=" + HttpHeaders.COMMENTS_SUMMARY])
    fun getCommentsSummary(@PathVariable productId: Long): CommentsSummary {
        return commentService.getCommentsSummary(productId)
    }
}