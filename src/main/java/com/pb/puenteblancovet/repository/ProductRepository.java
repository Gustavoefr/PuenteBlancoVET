package com.puenteblanco.pb.repository;

import com.puenteblanco.pb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    Product findBySlug(String slug);
}