package ru.samara.giftshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.CommentDto;
import ru.samara.giftshop.dto.CommentsSummary;
import ru.samara.giftshop.entity.Comment;
import ru.samara.giftshop.helpers.HttpHeaders;
import ru.samara.giftshop.service.CommentService;
import ru.samara.giftshop.service.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController extends BaseController {

    private final CommentService commentService;
    private final ProductService productService;

    @PostMapping("/comments")
    public CommentDto addComment(@RequestBody CommentDto comment) {
        return commentService.addComment(comment);
    }

    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @GetMapping("/products/{productId}/comments")
    public List<CommentDto> getCommentsByProduct(@PathVariable Long productId) {
        return productService.getCommentsByProductId(productId);
    }

    @GetMapping(value = "/products/{productId}/comments",
            headers = HttpHeaders.PRODUCE_VIEW + "=" + HttpHeaders.COMMENTS_SUMMARY)
    public CommentsSummary getCommentsSummary(@PathVariable Long productId) {
        return commentService.getCommentsSummary(productId);
    }
}