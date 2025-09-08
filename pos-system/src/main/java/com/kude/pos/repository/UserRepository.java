package com.kude.pos.repository;

import com.kude.pos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    findByUsername(String username);
    Optional<User> findByEmail(String email);
}
