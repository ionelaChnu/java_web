package model;

import java.util.Objects;

public class ProductToOrder {

    private Product product;

    private int quantity;

    private Double amount;

    public Product getProduct() {
        return product;
    }

    public ProductToOrder setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductToOrder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public ProductToOrder setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductToOrder that = (ProductToOrder) o;
        return amount == that.amount &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, amount);
    }

    @Override
    public String toString() {
        return "ProductToOrder{" +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }
}
