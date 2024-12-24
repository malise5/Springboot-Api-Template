package com.pokemonreview.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.model.Review;
import com.pokemonreview.api.service.impl.ReviewImp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/")
public class ReviewController {

    final ReviewImp reviewService;

    public ReviewController(ReviewImp reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsByPokemonId(@PathVariable Long pokemonId) {
        List<ReviewDTO> reviews = reviewService.getReviewByPokemonId(pokemonId);
        return ResponseEntity.ok().body(reviews);

    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long pokemonId, @PathVariable Long reviewId) {
        ReviewDTO review = reviewService.getReviewById(pokemonId, reviewId);
        return ResponseEntity.ok().body(review);
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO, @PathVariable Long pokemonId) {
        ReviewDTO entity = reviewService.createReview(reviewDTO, pokemonId);
        return new ResponseEntity<> (entity, HttpStatus.CREATED);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO, @PathVariable Long pokemonId, @PathVariable Long reviewId) {
        ReviewDTO entity = reviewService.updateReview(reviewDTO, pokemonId, reviewId);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long pokemonId, @PathVariable Long reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);
        return ResponseEntity.noContent().build();
    }

}
