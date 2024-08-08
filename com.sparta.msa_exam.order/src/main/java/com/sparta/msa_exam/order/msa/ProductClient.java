package com.sparta.msa_exam.order.msa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 필수 기능 3.1: FeignClient를 이용해서 주문 서비스에 상품 서비스 클라이언트 연결
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/product/search/{id}")
    Boolean existsProduct(@PathVariable("id") Long id);

    @GetMapping("/product/{id}")
    String getProduct(@PathVariable("id") Long id);

}
