package com.nagarajan.ecommerse_service.Model.Cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nagarajan.ecommerse_service.Model.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
   @OneToOne
   @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    @JsonManagedReference
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
