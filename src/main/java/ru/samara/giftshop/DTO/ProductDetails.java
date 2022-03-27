package ru.samara.giftshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetails extends ProductDTO{
    private String description;

    public ProductDetails(String name, Double price, String imgSource,
                          Integer height, Long categoryId, String description) {

        super(name, price, imgSource, height, categoryId);
        this.description = description;
    }
}
