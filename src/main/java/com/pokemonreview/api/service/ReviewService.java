package com.pokemonreview.api.service;

import java.util.List;

import com.pokemonreview.api.dto.ReviewDTO;

public interface ReviewService {
    
    List<ReviewDTO> getReviewByPokemonId(Long pokemonId);

    ReviewDTO createReview(ReviewDTO reviewDTO, Long pokemonId);

    ReviewDTO updateReview(ReviewDTO reviewDTO, Long pokemonId, Long reviewId);

    ReviewDTO getReviewById(Long pokemonId, Long reviewId);

    void deleteReview(Long pokemonId, Long reviewId);


}
