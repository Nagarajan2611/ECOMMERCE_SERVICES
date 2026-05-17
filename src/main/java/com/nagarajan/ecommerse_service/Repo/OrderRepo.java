package com.nagarajan.ecommerse_service.Repo;

import com.nagarajan.ecommerse_service.Model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long>, JpaSpecificationExecutor<Order> {
    Optional<Order> findByStatus(String status);
}
