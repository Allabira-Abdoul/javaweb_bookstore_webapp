package com.group1.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.bookstore.model.Review;
import com.group1.bookstore.model.User;
import com.group1.bookstore.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.findByUser(user);
    }

    @Override
    public Review updateReview(Review review, Long id) {
        Review existingReview = reviewRepository.findById(id).orElse(null);
        
        existingReview.setRating(review.getRating());
        existingReview.setReviewText(review.getReviewText());
        
        return reviewRepository.save(existingReview);
    }
    
}
