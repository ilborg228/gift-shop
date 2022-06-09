package ru.samara.giftshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private Double price;

    @JsonProperty("img_source")
    private String imgSource;

    @JsonProperty("category_id")
    private Long categoryId;
}
