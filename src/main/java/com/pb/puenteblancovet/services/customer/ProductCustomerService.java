package com.puenteblanco.pb.services.customer;

import com.puenteblanco.pb.dto.response.ProductSummaryResponse;
import com.puenteblanco.pb.entity.Product;
import com.puenteblanco.pb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCustomerService {

    private final ProductRepository productRepository;

    public ProductCustomerService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductSummaryResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToSummary)
                .collect(Collectors.toList());
    }

    public ProductSummaryResponse getBySlug(String slug) {
        Product product = productRepository.findBySlug(slug);
        return mapToSummary(product);
    }

    private ProductSummaryResponse mapToSummary(Product product) {
        ProductSummaryResponse dto = new ProductSummaryResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSlug(product.getSlug());
        dto.setImage(product.getImage());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }
}
