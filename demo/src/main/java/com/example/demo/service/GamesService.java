package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.GamesRepository;

@Service
public class GamesService {

	@Autowired
	private GamesRepository repository;

	public Optional<Games> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Games> findAll() {
		return repository.findAll();
	}

	public void save(Games games) {
		repository.save(games);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}


