package com.puenteblanco.pb.services.report;

import com.puenteblanco.pb.dto.response.OrderItemResponse;
import com.puenteblanco.pb.dto.response.OrderResponse;
import com.puenteblanco.pb.exception.ResourceNotFoundException;
import com.puenteblanco.pb.repository.OrderRepository;
import com.puenteblanco.pb.services.customer.OrderCustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderReceiptReport extends AbstractBaseReportService {

    private final OrderRepository orderRepository;
    private final OrderCustomerService orderService;

    public OrderReceiptReport(OrderRepository orderRepository, OrderCustomerService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    public byte[] generateReceipt(Long orderId) {
        OrderResponse order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("orderId", order.getOrderId());
        dataModel.put("status", order.getStatus());
        dataModel.put("totalPrice", order.getTotalPrice());
        dataModel.put("createdAt", order.getCreatedAt());
        dataModel.put("items", order.getItems());

        return generateReport(dataModel, "order-receipt"); // se espera order-receipt.ftl
    }
}
