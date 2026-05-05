package com.nagarajan.ecommerse_service.Model.Cart;

import com.nagarajan.ecommerse_service.Model.User.User;
import jakarta.persistence.*;

import java.util.List;

public class CartRequest {

    private User user;

    private List<CartItems> Items;

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
}
