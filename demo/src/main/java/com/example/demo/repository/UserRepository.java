package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    public Page<User> findAll(Pageable page);



}


