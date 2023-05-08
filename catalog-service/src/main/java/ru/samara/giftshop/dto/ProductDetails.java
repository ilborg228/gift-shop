package ru.samara.giftshop.dto;

import lombok.*;

import java.util.List;

@Data
public class ProductDetails extends ProductDto {
    private String description;
    private Boolean inStock;
    private List<String> images;
}
