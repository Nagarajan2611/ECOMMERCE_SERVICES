package com.nagarajan.ecommerse_service.Model.Product;

import com.nagarajan.ecommerse_service.Model.Cart.CartItems;
import com.nagarajan.ecommerse_service.Model.Order.OrderItems;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String description;
    private String name;
    private double price;
    private long stock;
    private LocalDate date;
    @OneToMany(mappedBy = "product")
    private List<CartItems> cartItemsList;
    @OneToMany(mappedBy = "product")
    private List<OrderItems> orderItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
