package com.example.herewegoagain.Review.Service;

import com.example.herewegoagain.Review.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByIndex(String index);
}
