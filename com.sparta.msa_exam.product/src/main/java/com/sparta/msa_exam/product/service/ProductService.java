package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @CachePut(cacheNames = "productAllCache", key = "#result.productId")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(cacheNames = "productAllCache", key = "methodName")
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream().map(ProductDto::fromEntity)
                .toList();
    }

    public String findById(Long id) {
       return String.valueOf(productRepository.findById(id)
                                        .orElseThrow()
                                        .getProductId());
    }

    public Product searchById(Long id) {
        return productRepository.findById(id)
                .orElse(null);
    }
}
