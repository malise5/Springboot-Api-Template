package com.pokemonreview.api.dto;


import lombok.Data;

@Data
public class ReviewDTO {

    private Long id;
    private String title;
    private String content;
    private float rating;
    // private Long pokemonId;  // Only include necessary information from the related entity
    // private String pokemonName;
    // private Review review;
    
}
