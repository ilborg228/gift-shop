package ru.samara.giftshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.samara.giftshop.dto.OrderDto;
import ru.samara.giftshop.dto.OrderListDto;
import ru.samara.giftshop.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController extends BaseController {

    private final OrderService orderService;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToCart(@RequestBody OrderDto req) {
        orderService.addProductToCart(req);
    }

    @PostMapping("/orders/submit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submitOrder(@RequestBody OrderDto req) {
        orderService.submitOrder(req);
    }

    @DeleteMapping("/orders/{orderId}/products/{productId}")
    public OrderDto removeFromCart(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.removeFromCart(orderId, productId);
    }

    @GetMapping("/orders/{userId}")
    public OrderDto getOrder(@PathVariable Long userId) {
        return orderService.getOrder(userId);
    }

    @GetMapping("/orders")
    public OrderListDto getOrders(
            @RequestParam(required = false, name = ORDER_STATUS) Long statusId,
            @RequestParam(required = false, defaultValue = DEF_PARAM_PAGE) Integer page,
            @RequestParam(required = false, name = PAGE_SIZE, defaultValue = DEF_PARAM_PAGE_SIZE) Integer pageSize) {
        return orderService.getOrders(statusId, page, pageSize);
    }

    @PatchMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @RequestParam Long statusId) {
        orderService.updateStatus(id, statusId);
    }
}
