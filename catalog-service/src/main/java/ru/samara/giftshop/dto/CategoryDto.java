package ru.samara.giftshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String imgSource;
}
