package com.nagarajan.ecommerse_service.Model.Cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nagarajan.ecommerse_service.Model.Product.Product;
import jakarta.persistence.*;

@Entity
@Table(name ="cartsItems")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="cart_id")
    @JsonBackReference
    private Cart cart;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    private Double quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
