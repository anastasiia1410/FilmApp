package com.example.filmapp.domain.use_cases.movies_list

import com.example.filmapp.domain.entity.Movie

data class MoviesState(
    val movies: List<Movie>,
)