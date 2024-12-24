
package com.pokemonreview.api.dto;

import java.util.List;

import com.pokemonreview.api.model.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDTO {

    private Long id;
    private String name;
    private String type;
    // private List<ReviewDTO> reviews;


}
