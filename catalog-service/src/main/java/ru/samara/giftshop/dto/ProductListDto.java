package ru.samara.giftshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductListDto {
    private List<ProductDto> products;
    private Long count;
}
