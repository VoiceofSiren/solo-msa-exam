package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.msa.ProductClient;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    private final ProductClient productClient;

    @Transactional
    public Order createOrder(Order order) {
        List<OrderProduct> orderProductList = order.getProductIds();
        for (OrderProduct orderProduct: orderProductList) {
            // 필수 기능 3.2: 상품 목록 조회 API를 호출해서 파라미터로 받은 product_id 가 상품 목록에 존재하는지 검증
            Boolean productExists = productClient.existsProduct(orderProduct.getProductId());
            // 필수 기능 3.3: 존재할경우 주문에 상품을 추가하고, 존재하지 않는다면 주문에 저장하지 않음.
            if (productExists) {
                String productId = productClient.getProduct(orderProduct.getProductId());
                orderProduct.setOrder(order);
            } else {
                return null;
            }

        }
        order.setProductIds(orderProductList);
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        List<OrderProduct> orderProductList = order.getProductIds();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProductId(productId);
        orderProductList.add(orderProduct);
        order.setProductIds(orderProductList);
        return orderRepository.save(order);
    }

    public Order findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        // Proxy 강제 초기화
        order.getProductIds();
        return order;
    }
}
