package com.pokemonreview.api.mapper;

import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.model.Review;

public class ReviewMapper {

    // Convert Review entity to ReviewDTO
    public static ReviewDTO toDTO(Review review) {
        if (review == null) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setRating(review.getRating());
        // reviewDTO.setPokemonId(review.getPokemon().getId());
        // reviewDTO.setPokemonName(review.getPokemon().getName());

        return reviewDTO;

    }

    // Convert ReviewDTO to Review entity
    public static Review toEntity(ReviewDTO reviewDTO) {
        if (reviewDTO == null) {
            return null;
        }

        // Create Review entity from DTO
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setRating(reviewDTO.getRating());

        // Optionally, handle the Pokemon object if needed. This depends on your use case.
        // if (reviewDTO.getPokemonId() != null) {
        //     Pokemon pokemon = new Pokemon();  // You would fetch the Pokemon by its ID
        //     pokemon.setId(reviewDTO.getPokemonId());
        //     pokemon.setName(reviewDTO.getPokemonName());  // Only if name is provided in DTO
        //     review.setPokemon(pokemon);
        // }

        return review;
    }
}

