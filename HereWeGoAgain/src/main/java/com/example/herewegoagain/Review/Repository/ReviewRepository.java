package com.example.herewegoagain.Review.Repository;

import com.example.herewegoagain.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByHardware_Index(String index);
}