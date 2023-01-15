package ru.samara.giftshop.dto;

import lombok.*;

@Data
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String imageUrl;
    private Long parentId;
    private Boolean hasChild;
}
