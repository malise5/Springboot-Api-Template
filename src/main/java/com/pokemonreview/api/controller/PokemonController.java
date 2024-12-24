package com.pokemonreview.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.service.impl.PokemonImp;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    final PokemonImp pokemonImp;

    public PokemonController(PokemonImp pokemonImp) {
        this.pokemonImp = pokemonImp;
    }

    @GetMapping("pokemons")
    public ResponseEntity<PokemonResponse> getListOfPokemon(
            @RequestParam(defaultValue = "0", required = false) Long pageNo,
            @RequestParam(defaultValue = "10", required = false) Long pageSize) {

        return ResponseEntity.ok().body(pokemonImp.getListOfPokemon(pageNo, pageSize));
    }

    @GetMapping("pokemons/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pokemonImp.getPokemonById(id));
    }

    @PostMapping("pokemon")
    public ResponseEntity<PokemonDTO> addPokemon(@RequestBody PokemonDTO pokemonDto) {
        return new ResponseEntity<>(pokemonImp.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("pokemons/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable Long id, @RequestBody PokemonDTO pokemonDto) {

        return ResponseEntity.ok().body(pokemonImp.updatePokemon(id, pokemonDto));
    }

    @DeleteMapping("pokemons/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable Long id) {
        pokemonImp.deletePokemon(id);
        return ResponseEntity.status(HttpStatus.GONE).body("Pokemon with id " + id + " deleted successfully");
    }

}
