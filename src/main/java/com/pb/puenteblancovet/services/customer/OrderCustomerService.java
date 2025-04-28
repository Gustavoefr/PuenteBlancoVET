package com.puenteblanco.pb.services.customer;

import com.puenteblanco.pb.dto.request.CartItemRequest;
import com.puenteblanco.pb.dto.request.OrderRequest;
import com.puenteblanco.pb.dto.response.OrderItemResponse;
import com.puenteblanco.pb.dto.response.OrderResponse;
import com.puenteblanco.pb.entity.Order;
import com.puenteblanco.pb.entity.OrderItem;
import com.puenteblanco.pb.entity.Product;
import com.puenteblanco.pb.entity.User;
import com.puenteblanco.pb.exception.ResourceNotFoundException;
import com.puenteblanco.pb.repository.OrderRepository;
import com.puenteblanco.pb.repository.ProductRepository;
import com.puenteblanco.pb.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderCustomerService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderCustomerService(OrderRepository orderRepository,ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createOrder(Long userId, OrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        double total = 0.0;
        Set<OrderItem> orderItems = new HashSet<>();

        for (CartItemRequest item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: ID " + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);
            total += product.getPrice() * item.getQuantity();
        }

        order.setItems(orderItems);
        order.setTotalPrice(total);

        orderRepository.save(order);
    }

    public List<OrderResponse> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.setOrderId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponse> itemDTOs = order.getItems().stream().map(item -> {
            OrderItemResponse i = new OrderItemResponse();
            i.setProductId(item.getProduct().getId());
            i.setProductName(item.getProduct().getName());
            i.setPrice(item.getPrice());
            i.setQuantity(item.getQuantity());
            i.setSubtotal(item.getPrice() * item.getQuantity());
            return i;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

    public Optional<OrderResponse> getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::mapToOrderResponse);
    }
}