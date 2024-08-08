package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
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
