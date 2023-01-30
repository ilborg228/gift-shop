package ru.samara.giftshop.dto;

import lombok.Data;
import ru.samara.giftshop.entity.User;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<ProductDto> products;
    private Long userId;
    private String address;
    private Date orderCreation;
}
