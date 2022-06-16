package com.example.demo.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import javax.persistence.*;

@Entity(name = "UserTable")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	@ManyToMany(mappedBy="users")
	private List<Game> gamesBought;

	public User() {
	}

	public User(String name, String encodedPassword, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.roles = List.of(roles);
	}



	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setPassword(String password) {
		this.encodedPassword = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Game> getGames() {
		return gamesBought;
	}

	public void setBooks(List<Game> books) {
		this.gamesBought = gamesBought;
	}

}

