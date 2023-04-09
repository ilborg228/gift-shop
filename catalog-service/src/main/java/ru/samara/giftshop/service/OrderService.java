package ru.samara.giftshop.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.samara.giftshop.dto.DtoMapper;
import ru.samara.giftshop.dto.OrderDto;
import ru.samara.giftshop.dto.OrderListDto;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.Order;
import ru.samara.giftshop.entity.Product;
import ru.samara.giftshop.entity.User;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataNotFoundResponse;
import ru.samara.giftshop.helpers.OrderBy;
import ru.samara.giftshop.repository.OrderRepository;
import ru.samara.giftshop.repository.ProductRepository;
import ru.samara.giftshop.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService extends BaseService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void addProductToCart(OrderDto order) {
        addProductToCart(order.getProducts().get(0).getId(), order.getUserId());
    }

    private void addProductToCart(Long productId, Long userId) {
        notNull(productId);
        notNull(userId);

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ApiException(DataNotFoundResponse.PRODUCT_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ApiException(DataNotFoundResponse.USER_NOT_FOUND));
        Optional<Order> opt = orderRepository.findOrderByUserIdAndStatus(userId, Order.Status.CREATED);
        if (opt.isPresent()) {
            List<Product> products = opt.get().getProducts();
            products.add(product);
            opt.get().setProducts(products);
            orderRepository.save(opt.get());
        } else {
            Order order = new Order();
            order.setProducts(List.of(product));
            order.setUser(user);
            order.setOrderCreation(new Date());
            order.setStatus(Order.Status.CREATED);
            orderRepository.save(order);
        }
    }

    public OrderDto removeFromCart(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new ApiException(DataNotFoundResponse.ORDER_NOT_FOUND));

        Product toRemove = order.getProducts()
                .stream()
                .filter(p-> Objects.equals(p.getId(), productId))
                .findFirst().get();
        order.getProducts().remove(toRemove);

        orderRepository.save(order);
        return DtoMapper.toOrderDto(order);
    }

    public OrderDto getOrder(Long userId) {
        notNull(userId);

        return DtoMapper.toOrderDto(
                orderRepository
                        .findOrderByUserIdAndStatus(userId, Order.Status.CREATED)
                        .orElseThrow(() ->
                                new ApiException(DataNotFoundResponse.ORDER_NOT_FOUND)
                        )
        );
    }

    public void submitOrder(OrderDto req) {
        notNull(req.getAddress());
        notNull(req.getId());

        Order order = orderRepository.findOrderByIdAndStatus(req.getId(), Order.Status.CREATED)
                .orElseThrow(() -> new ApiException(DataNotFoundResponse.ORDER_NOT_FOUND));

        order.setStatus(Order.Status.SUBMITTED);
        order.setAddress(req.getAddress());

        orderRepository.save(order);
    }

    public OrderListDto getOrders(Integer page, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, OrderBy.ORDER_CREATION.getColumn());
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<OrderDto> orders = orderRepository.findAllByStatusIsNot(Order.Status.CREATED, pageable).stream().map(DtoMapper::toOrderDto).collect(Collectors.toList());

        Long count = orderRepository.countAllByStatusIsNot(Order.Status.CREATED);

        return new OrderListDto(orders, count);
    }

    @Transactional
    public void updateStatus(Long id, Long statusId) {
        Order.Status status = Order.Status.getByStatusId(statusId);
        orderRepository.updateStatus(id, status);
    }
}
