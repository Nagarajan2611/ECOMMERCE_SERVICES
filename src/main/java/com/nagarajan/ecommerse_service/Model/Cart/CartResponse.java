package com.nagarajan.ecommerse_service.Model.Cart;

import com.nagarajan.ecommerse_service.Model.User.User;
import java.time.LocalDate;
import java.util.List;

public class CartResponse {

    private long id;
    private User user;
    private List<CartItems> Items;
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItems> getItems() {
        return Items;
    }

    public void setItems(List<CartItems> items) {
        Items = items;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
