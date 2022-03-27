package ru.samara.giftshop.DTO;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
public class ProductDetails extends ProductDTO{
    private String description;

    private Integer height;
}
