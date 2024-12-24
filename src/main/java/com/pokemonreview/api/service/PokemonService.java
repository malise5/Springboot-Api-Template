package com.pokemonreview.api.service;

import java.util.List;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.PokemonResponse;

// @Service
public interface PokemonService {

    // public List<PokemonDTO> getListOfPokemon(Long pageNo, Long pageSize);
    public PokemonResponse getListOfPokemon(Long pageNo, Long pageSize);
    public PokemonDTO createPokemon(PokemonDTO pokemonDto);
    public PokemonDTO updatePokemon(Long id, PokemonDTO pokemonDto);
    public PokemonDTO getPokemonById(Long id);
    public void deletePokemon(Long id);
    

}
