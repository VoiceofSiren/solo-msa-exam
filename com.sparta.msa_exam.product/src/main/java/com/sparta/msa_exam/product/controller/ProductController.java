package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import com.sparta.msa_exam.product.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}")
    private String serverPort;

    // 필수 기능 1.1: 상품 추가 API
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product,

                                 HttpServletResponse response) {
        response.addHeader("Server-Port", serverPort);
        return productService.save(product);
    }


    // 필수 기능 1.2: 상품 목록 조회 API
    @GetMapping("/products")
    public List<Product> getProducts(HttpServletResponse response) {
        response.addHeader("Server-Port", serverPort);
        return productService.findAll();
    }

    // 필수 기능 1.3 관련 - id로 Product를 조회
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    // 필수 기능 3.3 관련 - id로 Product가 있는지 검사
    @GetMapping("/product/search/{id}")
    Boolean existsProduct(@PathVariable("id") Long id) {
        Product productToFind = productService.searchById(id);
        if (productToFind == null) {
            return false;
        } else {
            return  true;
        }
    }
}