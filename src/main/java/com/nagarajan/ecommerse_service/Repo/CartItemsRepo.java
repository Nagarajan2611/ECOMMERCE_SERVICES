package com.nagarajan.ecommerse_service.Repo;

import com.nagarajan.ecommerse_service.Model.Cart.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
}
