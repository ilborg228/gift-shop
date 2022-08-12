package ru.samara.giftshop.dto;

import lombok.Data;

@Data
public class ProductImageDto {
    private Long id;
    private String imgSource;
    private Boolean primaryImage;
}
