package ru.samara.giftshop.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
public class ProductDetails extends ProductDTO{
    private String description;

    private Integer height;
}
