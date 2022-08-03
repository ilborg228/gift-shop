package ru.samara.giftshop.dto;

import lombok.*;

@Data
public class ProductDetails extends ProductDto {
    private String description;
    private Integer height;
}
