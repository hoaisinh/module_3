package com.example.tekushop.model;

import java.time.LocalDate;

public class Clothing {
    private int id;
    private String name;
    private String clothesType;
    private String color;
    private int size;
    private LocalDate dateAdded;
    private LocalDate dateModified;
    private String[] images;
    private int price;
    private int stock;
    private int sold;
    private String description;

    public Clothing(int id, String name, String clothesType, String color, int size, LocalDate dateAdded, LocalDate dateModified, String[] images, int price, int stock, int sold, String description) {
        this.id = id;
        this.name = name;
        this.clothesType = clothesType;
        this.color = color;
        this.size = size;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.images = images;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.description = description;
    }

    public Clothing(String name, String clothesType, String color, int size, LocalDate dateAdded, LocalDate dateModified, String[] images, int price, int stock, int sold, String description) {
        this.name = name;
        this.clothesType = clothesType;
        this.color = color;
        this.size = size;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.images = images;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.description = description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClothesType() {
        return clothesType;
    }

    public void setClothesType(String clothesType) {
        this.clothesType = clothesType;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
