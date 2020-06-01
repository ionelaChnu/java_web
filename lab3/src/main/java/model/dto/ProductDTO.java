package model.dto;

import model.ProductToOrder;
import java.util.UUID;

public class ProductDTO {

    private UUID id;

    private String name;

    private int quantity;

    private Double price;

    private Double totalPrice;

    public UUID getId() {
        return id;
    }

    public ProductDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public ProductDTO setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public static ProductDTO makeDTO(ProductToOrder productToOrder) {
        return new ProductDTO()
                .setId(productToOrder.getProduct().getId())
                .setName(productToOrder.getProduct().getName())
                .setQuantity(productToOrder.getQuantity())
                .setPrice(productToOrder.getProduct().getPrice())
                .setTotalPrice(productToOrder.getAmount());
    }
}
