package ru.samara.giftshop.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
    private Long categoryId;
}
