package ru.samara.giftshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.samara.giftshop.entity.Product;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO{
    private String categoryName;

    private String imgSource;

    private List<Product> products;
}
