package com.example.demo.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Purchase")
public class Purchase {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String surname;
    private String address;
    private int postalCode;
    private String city;
    private String country;
    private int phoneNumber;
    private float price;
    private int dateDay;
    private int dateMonth;
    private int dateYear;

    @ManyToMany
    @JoinTable(
            name="Pur_Game",
            joinColumns=@JoinColumn(name="Pur_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="Game_id", referencedColumnName="id"))
    private List<Game> games;

    @ManyToOne
    @JoinColumn(name="Users_id", nullable=true)
    private User users;

    public Purchase() {
    }

    public Purchase(String firstName, String surname, String address, int postalCode, String city, String country, int phoneNumber, User user,List<Game> games,int day, int month, int year) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.users = user;
        this.games = games;
        this.dateDay = day;
        this.dateMonth = month;
        this.dateYear = year;
        this.price = 0;
    }
    public Purchase(String firstName, String surname, String address, int postalCode, String city, String country, int phoneNumber, float price) {
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public int getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public float getPrice() {
        return price;
    }


    public void setPrice(float price) {
        this.price = price;
    }


    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return users;
    }

    public void setUser(User user) {
        this.users = user;
    }

    public int getDateDay() {
        return dateDay;
    }

    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public int getDateYear() {
        return dateYear;
    }

    public void setDateYear(int dateYear) {
        this.dateYear = dateYear;
    }



}