package com.puenteblanco.pb.services.customer;

import com.puenteblanco.pb.dto.request.CartItemRequest;
import com.puenteblanco.pb.dto.response.CartItemResponse;
import com.puenteblanco.pb.entity.Product;
import com.puenteblanco.pb.exception.ResourceNotFoundException;
import com.puenteblanco.pb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    // Simulación de carrito por usuario (en producción usar persistencia)
    private final Map<Long, Map<Long, Integer>> carts = new HashMap<>();
    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addItem(Long userId, CartItemRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        carts.putIfAbsent(userId, new HashMap<>());
        Map<Long, Integer> cart = carts.get(userId);
        cart.put(product.getId(), cart.getOrDefault(product.getId(), 0) + request.getQuantity());
    }

    public void updateItem(Long userId, CartItemRequest request) {
        Map<Long, Integer> cart = carts.get(userId);
        if (cart == null || !cart.containsKey(request.getProductId())) {
            throw new ResourceNotFoundException("El producto no está en el carrito");
        }
        cart.put(request.getProductId(), request.getQuantity());
    }

    public void removeItem(Long userId, Long productId) {
        Map<Long, Integer> cart = carts.get(userId);
        if (cart != null) {
            cart.remove(productId);
        }
    }

    public List<CartItemResponse> getCartItems(Long userId) {
        Map<Long, Integer> cart = carts.getOrDefault(userId, Collections.emptyMap());

        List<CartItemResponse> responseList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product product = productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            CartItemResponse response = new CartItemResponse();
            response.setProductId(product.getId());
            response.setProductName(product.getName());
            response.setImage(product.getImage());
            response.setPrice(product.getPrice());
            response.setQuantity(entry.getValue());
            response.setSubtotal(product.getPrice() * entry.getValue());

            responseList.add(response);
        }

        return responseList;
    }
}