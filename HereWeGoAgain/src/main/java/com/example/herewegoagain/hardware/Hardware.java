package com.example.herewegoagain.hardware;

import com.example.herewegoagain.Review.Review;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String index;

    private String name;

    private Double price;

    private String type;

    private Integer inStock;

    @OneToMany(targetEntity = Review.class, mappedBy = "hardware")
    private List<Review> reviews;

    public Hardware(){}


    public Hardware(String index, String name, Double price, String type, Integer inStock) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.type = type;
        this.inStock = inStock;
    }

    public Hardware(String name, Double price, String type, Integer inStock) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
