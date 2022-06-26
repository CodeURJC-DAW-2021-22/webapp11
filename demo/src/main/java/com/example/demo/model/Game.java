package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Blob;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Game")
@DynamicUpdate
public class Game {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	private String title;
	private float gamePrice;
	private String category;
	private int nbuy;


	@Column(columnDefinition = "TEXT")
	private String description;



	@Lob
	@JsonIgnore
	private Blob imageFile;

	private boolean image;



	public Game() {}

	public Game(String title, String description, float gamePrice, String category) {
		super();
		this.title = title;
		this.description = description;
		this.gamePrice = gamePrice;
		this.category = category;
		this.nbuy = 0;
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

	public float getgamePrice() {
		return gamePrice;
		}

	public void setgamePrice(float price) {
		this.gamePrice = gamePrice;
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

	public int getNbuy() {
		return nbuy;
	}

	public void setNbuy(int nbuy) {
		this.nbuy = nbuy;
	}


	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", description=" + description + ", price=" + gamePrice + ", category=" + category + "]";
	}
}
