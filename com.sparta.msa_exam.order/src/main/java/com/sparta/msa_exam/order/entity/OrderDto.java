package com.sparta.msa_exam.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private List<OrderProduct> productIds = new ArrayList<>();

    public static OrderDto fromOrder(Order order) {
        return OrderDto.builder()
                    .orderId(order.getOrderId())
                    .productIds(order.getProductIds())
                    .build();
    }
}