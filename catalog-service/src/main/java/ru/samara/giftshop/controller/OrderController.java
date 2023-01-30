package ru.samara.giftshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.OrderDto;
import ru.samara.giftshop.entity.Order;
import ru.samara.giftshop.service.OrderService;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToCart(@RequestBody OrderDto order) {
        orderService.addProductToCart(order);
    }

    @DeleteMapping("/orders/{orderId}/products/{productId}")
    public OrderDto removeFromCart(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.removeFromCart(orderId, productId);
    }

    @GetMapping("/orders")
    public OrderDto getOrder(@RequestParam Long userId) {
        return orderService.getOrder(userId);
    }
}
