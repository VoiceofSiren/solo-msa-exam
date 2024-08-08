package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderDto;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    @Value("${server.port}")
    private String serverPort;

    private final OrderService orderService;

    // 필수 기능 1.3: 주문 추가 API
    @PostMapping("/order")
    public String createOrder(@RequestBody Order order,
                             HttpServletResponse response) {
        Order createdOrder = orderService.createOrder(order);
        response.addHeader("Server-Port", serverPort);
        String result = "Order created.";
        if (createdOrder == null) {
            result = "Order not created.";
        }
        return result;
    }

    // 필수 기능 1.4: 주문에 상품을 추가하는 API
    @PutMapping("/order/{orderId}")
    public String updateOrder(@PathVariable(name = "orderId") Long orderId,
                             @RequestBody OrderProduct orderProduct,
                             HttpServletResponse response) {
        Order updatedOrder = orderService.updateOrder(orderId, orderProduct.getProductId());
        response.addHeader("Server-Port", serverPort);
        String result = "Order updated.";
        if (updatedOrder == null) {
            result = "Order not updated.";
        }
        return result;
    }

    // 필수 기능 1.5: 주문 단건 조회 API
    @GetMapping("/order/{orderId}")
    public OrderDto getAnOrder(@PathVariable(name = "orderId") Long orderId,
                               HttpServletResponse response) {
        Order foundOrder = orderService.findById(orderId);
        response.addHeader("Server-Port", serverPort);
        return OrderDto.builder()
                .orderId(foundOrder.getOrderId())
                .productIds(foundOrder.getProductIds())
                .build();
    }

}
