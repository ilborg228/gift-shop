package ru.samara.giftshop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.samara.giftshop.dto.CommentDto;
import ru.samara.giftshop.dto.CommentsSummary;
import ru.samara.giftshop.dto.DtoMapper;
import ru.samara.giftshop.entity.Comment;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataNotFoundResponse;
import ru.samara.giftshop.exceptions.DataValidationResponse;
import ru.samara.giftshop.repository.CommentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends BaseService {

    @Value("${comments-service.comment-minimum-length}")
    private Integer commentLength;
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto addComment(CommentDto data) {
        if (StringUtils.hasText(data.getText()) && data.getText().length() > commentLength) {
            data.setCreation(new Date());
            return DtoMapper.toCommentDto(commentRepository.save(DtoMapper.toComment(data)));
        } else {
            throw new ApiException(DataValidationResponse.COMMENT_LENGTH_IS_NOT_CORRET, this.commentLength);
        }
    }

    public final void editComment(long id, Comment comment) {
        Optional<Comment> op = this.commentRepository.findById(id);
        if (op.isPresent()) {
            Comment old = op.get();
            if (old.getText() != null && comment.getText() == null) {
                comment.setText(old.getText());
            }
            commentRepository.save(comment);
        } else {
            throw new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND);
        }
    }

    public final CommentsSummary getCommentsSummary(long productId) {
        List<Comment> comments = commentRepository
                .findAllByProduct(new Product(productId));
        CommentsSummary res = new CommentsSummary();
        res.setAverageScore(comments.stream()
                .mapToInt(Comment::getScoreValue)
                .average().orElse(0));
        res.setCount(comments.size());
        return res;
    }
}
