package model.dto;

import model.Order;
import model.ProductToOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartDTO {

    private Double total = 0.;

    private List<ProductDTO> products = new ArrayList<>();

    public Double getTotal() {
        return total;
    }

    public CartDTO setTotal(Double total) {
        this.total = total;
        return this;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public CartDTO setProducts(List<ProductDTO> products) {
        this.products = products;
        return this;
    }

    public static OrderDetailsDTO makeOrderDetailsDTO(Order order) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        buildDTO(dto, order);

        return dto.setDate(order.getDate())
                .setStatus(order.getStatus());
    }

    public static CartDTO makeDTO(Optional<Order> optionalOrder) {
        if (!optionalOrder.isPresent()) {
            return new CartDTO();
        }
        Order order = optionalOrder.get();
        CartDTO dto = new CartDTO();
        return buildDTO(dto, order);
    }

    public static <T extends CartDTO> T buildDTO(T dto, Order order) {
        dto.setProducts(order.getItems().stream().map(ProductDTO::makeDTO).collect(Collectors.toList()))
                .setTotal(order.getItems().stream().map(ProductToOrder::getAmount).mapToDouble(Double::doubleValue).sum());
        return dto;
    }
}
