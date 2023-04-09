package ru.samara.giftshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderListDto {
    private List<OrderDto> orders;
    private Long count;
}
