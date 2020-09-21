package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(Integer price);
    List<Product> findAllByPriceLessThanEqual(Integer price);
    List<Product> findAllByPriceGreaterThanEqualAndPriceIsLessThanEqual(Integer minPrice, Integer maxPrice);
}
