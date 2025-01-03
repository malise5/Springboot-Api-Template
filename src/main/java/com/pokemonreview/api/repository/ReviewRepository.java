package com.pokemonreview.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonreview.api.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByPokemonId(Long pokemonId);
    
    
}
