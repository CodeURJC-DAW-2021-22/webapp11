package com.example.demo.model;

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

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String title;

	private Long price;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	private String category;

	@Lob
	@JsonIgnore
	private Blob imageFile;

	private boolean image;

	@ManyToMany
 	private List<User> users;



	public Game() {}

	public Game(String nombre, String description, Long price, String category) {
		super();
		this.title = nombre;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() { return price; }

	public void setPrice(String description) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	public boolean getImage(){
		return this.image;
	}

	public void setImage(boolean image){
		this.image = image;
	}

	//public List<User> getShops() { return users; }

	//public void setShops(List<User> shops) { this.users = users; }



	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price + ", category=" + category + "]";
	}
}
