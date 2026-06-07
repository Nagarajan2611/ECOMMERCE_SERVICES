package com.nagarajan.ecommerse_service.Repo;

import com.nagarajan.ecommerse_service.Model.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long>{
    List<Cart> findByUserId(long id);
}
