package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.example.demo.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;

	public Optional<Game> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public Page<Game> findAll(int n) {
		return repository.findAll( PageRequest.of(n, 5));
	}

	public void save(Game game) {
		repository.save(game);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}


}


