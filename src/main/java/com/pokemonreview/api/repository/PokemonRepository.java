package com.pokemonreview.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonreview.api.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    
}
