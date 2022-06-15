package com.example.herewegoagain.Review.Service;

import com.example.herewegoagain.Review.Repository.ReviewRepository;
import com.example.herewegoagain.Review.Review;
import com.example.herewegoagain.Review.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewClassService implements ReviewService{

    private final ReviewRepository reviewRepository;

    public ReviewClassService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByIndex(String index) {
        return reviewRepository.findAllByHardware_Index(index).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    private ReviewDTO mapReviewToDTO(Review review){
        return new ReviewDTO(review.getTitle(), review.getBody(), review.getRating());
    }
}

