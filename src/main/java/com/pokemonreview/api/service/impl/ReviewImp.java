package com.pokemonreview.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.exceptions.ReviewNotFoundException;
import com.pokemonreview.api.mapper.ReviewMapper;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.model.Review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.ReviewService;

@Service
public class ReviewImp implements ReviewService{

    final ReviewRepository reviewRepository;

    final PokemonRepository pokemonRepository;

    public ReviewImp(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<ReviewDTO> getReviewByPokemonId(Long pokemonId) {
        
        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("No reviews found for pokemon with id: " + pokemonId);
        }

        return reviews.stream().map(review -> ReviewMapper.toDTO(review)).toList();
        
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO, Long pokemonId) {
        
        Review review = ReviewMapper.toEntity(reviewDTO);

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ReviewNotFoundException("Pokemon not found with id: " + pokemonId));

        review.setPokemon(pokemon);
        
        Review savedReview = reviewRepository.save(review);

        return ReviewMapper.toDTO(savedReview);
    }

    @Override
    public ReviewDTO getReviewById(Long pokemonId, Long reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ReviewNotFoundException("Pokemon not found with id: " + pokemonId));
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + reviewId));

        if (!review.getPokemon().getId().equals(pokemon.getId())) {
            throw new ReviewNotFoundException("Review not found with id: " + reviewId + " for pokemon with id: " + pokemonId);
        }

        return ReviewMapper.toDTO(review);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDTO, Long pokemonId, Long reviewId) {
        
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ReviewNotFoundException("Pokemon not found with id: " + pokemonId));
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + reviewId));

        if (!review.getPokemon().getId().equals(pokemon.getId())) {
            throw new ReviewNotFoundException("Review not found with id: " + reviewId + " for pokemon with id: " + pokemonId);
        }

        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());


        Review updatedReview = reviewRepository.save(review);

        return ReviewMapper.toDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long pokemonId, Long reviewId) {
        
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new ReviewNotFoundException("Pokemon not found with id: " + pokemonId));
        
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + reviewId));

        if (!review.getPokemon().getId().equals(pokemon.getId())) {
            throw new ReviewNotFoundException("Review not found with id: " + reviewId + " for pokemon with id: " + pokemonId);
        }

        reviewRepository.delete(review);
    }

    
    
}
