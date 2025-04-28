package com.puenteblanco.pb.repository;

import com.puenteblanco.pb.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Por ahora no es necesario personalizar, pero está listo para consultas futuras
}
