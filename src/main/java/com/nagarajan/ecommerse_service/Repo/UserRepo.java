package com.nagarajan.ecommerse_service.Repo;

import com.nagarajan.ecommerse_service.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByName(String name);
}
