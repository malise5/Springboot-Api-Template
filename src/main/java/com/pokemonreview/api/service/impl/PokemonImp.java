package com.pokemonreview.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.mapper.PokemonMapper;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;


@Service
public class PokemonImp implements PokemonService {

    final PokemonRepository pokemonRepository;

    public PokemonImp(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonResponse getListOfPokemon(Long pageNo, Long pageSize) {

        Pageable pageable = PageRequest.of(pageNo.intValue(), pageSize.intValue());

        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);

        List<Pokemon> pokemonsList = pokemons.getContent();

        List<PokemonDTO> content = pokemonsList.stream().map(pokemon -> PokemonMapper.toDTO(pokemon))
                .toList();
        
        PokemonResponse pokemonResponse = new PokemonResponse();

        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
        // return pokemonsList.stream().map(pokemon -> PokemonMapper.toDTO(pokemon))
        //         .toList();

    }

    @Override
    public PokemonDTO getPokemonById(Long id) {
        
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("No Pokemon found"));

        return PokemonMapper.toDTO(pokemon);

    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDto) {
        if (pokemonDto.getName() == null || pokemonDto.getType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Pokemon name or type cannot be empty");
        }
        Pokemon pokemon = PokemonMapper.toEntity(pokemonDto);

        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        
        return PokemonMapper.toDTO(savedPokemon);
    }

    @Override
    public PokemonDTO updatePokemon(Long id, PokemonDTO pokemonDto) {

        Pokemon existingPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException("No Pokemon found with id " + id + " to update"));

        if (pokemonDto.getName() != null) {
            existingPokemon.setName(pokemonDto.getName());
        }
        if (pokemonDto.getType() != null) {
            existingPokemon.setType(pokemonDto.getType());
        }

        Pokemon updatedPokemon = pokemonRepository.save(existingPokemon);
        return PokemonMapper.toDTO(updatedPokemon);

    }

    @Override
    public void deletePokemon(Long id) {
        if (pokemonRepository.findById(id).isEmpty()) {
            throw new PokemonNotFoundException("No Pokemon found with id " + id + " to delete");
        }
        pokemonRepository.deleteById(id);
    }
}
