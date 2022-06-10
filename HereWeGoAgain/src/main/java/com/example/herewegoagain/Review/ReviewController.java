package com.example.herewegoagain.Review;

import com.example.herewegoagain.Review.Service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping("/{index}")
    public List<ReviewDTO> getAllReviewsByHardware(@PathVariable String index){
        return reviewService.findAllByIndex(index);
    }
}

