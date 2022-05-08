package ru.samara.giftshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    private String name;

    private Double price;

    private String imgSource;

    private Long categoryId;
}
