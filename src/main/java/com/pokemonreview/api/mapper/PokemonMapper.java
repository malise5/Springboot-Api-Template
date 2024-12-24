package com.pokemonreview.api.mapper;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.model.Pokemon;

public class PokemonMapper {

     // Convert Pokemon entity to PokemonDTO
    public static PokemonDTO toDTO(Pokemon pokemon) {
        if (pokemon == null) {
            return null;
        }
        
        PokemonDTO pokemonDto = new PokemonDTO();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        // pokemonDto.setReviews(pokemon.getReviews());
        
        return pokemonDto;
    }

    // Convert PokemonDTO to Pokemon entity
    public static Pokemon toEntity(PokemonDTO pokemonDto) {
        if (pokemonDto == null) {
            return null;
        }
        
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        
        return pokemon;
    }
    
}
