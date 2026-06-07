package com.nagarajan.ecommerse_service.Model.Order;

import com.nagarajan.ecommerse_service.Model.User.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

public class OrderRequest {

    private List<OrderItems> items;

    public List<OrderItems> getItems() {
        return items;
    }
    public void setItems(List<OrderItems> items) {
        this.items = items;
    }
}
