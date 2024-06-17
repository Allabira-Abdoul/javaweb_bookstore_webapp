package com.group1.bookstore.service;

import java.util.List;

import com.group1.bookstore.model.Review;
import com.group1.bookstore.model.User;

public interface ReviewService {
    public Review saveReview(Review review);
    public List<Review> getAllReviews();
    public Review getReviewById(Long id);
    public List<Review> getReviewsByUser(User user);
    public Review updateReview(Review review, Long id);
}
