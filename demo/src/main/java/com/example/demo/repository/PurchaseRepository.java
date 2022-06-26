package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Game;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {


	 public Page<Purchase> getByUser(User u, Pageable page);

	 public Page<Purchase> findAll(Pageable page);

/**/
     @Query("SELECT p FROM Purchase p WHERE p.users = :u")

	 public List<Purchase> getByUser(User u);



     @Query("SELECT p.id FROM Purchase p")
	 public List<Long> findIdAll();

     @Query("SELECT p.price FROM Purchase p")
	 public List<Float> findPriceAll();




	 
}
