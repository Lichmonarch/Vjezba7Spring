package com.example.herewegoagain.Review;

public class ReviewDTO {
    private String title;
    private Integer rating;

    public ReviewDTO(String title, Integer rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}

