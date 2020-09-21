package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByMinPrice(Integer price) { return productRepository.findAllByPriceGreaterThanEqual(price); }

    public List<Product> findByMaxPrice(Integer price) { return productRepository.findAllByPriceLessThanEqual(price); }

    public List<Product> findByMinAndMaxPrice(Integer minPrice, Integer maxPrice) { return productRepository.findAllByPriceGreaterThanEqualAndPriceIsLessThanEqual(minPrice, maxPrice); }


}
