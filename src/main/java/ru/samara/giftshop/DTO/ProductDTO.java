package ru.samara.giftshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String name;

    private Double price;

    private String imgSource;

    private Integer height;

    private CategoryEntity category;
}
