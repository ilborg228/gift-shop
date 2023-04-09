package ru.samara.giftshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.samara.giftshop.entity.Order;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<ProductDto> products;
    private Long userId;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
    private Date orderCreation;
    private Long statusId;
}
