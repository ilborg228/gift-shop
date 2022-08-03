package ru.samara.giftshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private Long productId;
    private Date creation;
    private Integer scoreValue;
}
