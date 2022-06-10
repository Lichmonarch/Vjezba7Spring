package com.example.herewegoagain.Review;

import com.example.herewegoagain.hardware.Hardware;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String index;

    private String title;

    private String body;

    private Integer rating;

    @ManyToOne(targetEntity = Hardware.class)
    @JoinColumn(name = "id_hardware")
    private Hardware hardware;

    public String getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getRating() {
        return rating;
    }

    public Hardware getHardware() {
        return hardware;
    }
}
