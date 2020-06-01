package model.dto;

import model.Order;

import java.util.Optional;
import java.util.UUID;

public class CartInfoDTO {
    private UUID id;

    private int count;

    public UUID getId() {
        return id;
    }

    public CartInfoDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public int getCount() {
        return count;
    }

    public CartInfoDTO setCount(int count) {
        this.count = count;
        return this;
    }

    public static CartInfoDTO makeDTO(Optional<Order> optionalOrder) {
        if (!optionalOrder.isPresent()) {
            return new CartInfoDTO();
        }
        Order order = optionalOrder.get();
        return new CartInfoDTO()
                .setId(order.getId())
                .setCount(order.getItems().size());
    }
}
