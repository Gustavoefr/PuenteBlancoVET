package com.puenteblanco.pb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {

    @NotBlank(message = "La dirección de entrega es obligatoria")
    private String deliveryAddress;

    @NotNull(message = "La lista de productos no puede estar vacía")
    private List<CartItemRequest> items;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<CartItemRequest> getItems() {
        return items;
    }

    public void setItems(List<CartItemRequest> items) {
        this.items = items;
    }
}
