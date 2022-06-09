package ru.samara.giftshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long id;

    public String text;

    @JsonProperty("product_id")
    private Long productId;

    public Date creation;

    @JsonProperty("score_value")
    private String scoreValue;
}
