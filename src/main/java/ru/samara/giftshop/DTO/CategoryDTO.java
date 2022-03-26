package ru.samara.giftshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.samara.giftshop.entity.Product;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDTO{
    private String categoryName;

    private String imgSource;

    private Long categoryId;
}
