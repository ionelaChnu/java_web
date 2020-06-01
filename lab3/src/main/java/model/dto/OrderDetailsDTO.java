package model.dto;

import model.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderDetailsDTO extends CartDTO {

    private OrderStatus status;

    private LocalDateTime date;

    public OrderStatus getStatus() {
        return status;
    }

    public OrderDetailsDTO setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public OrderDetailsDTO setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
