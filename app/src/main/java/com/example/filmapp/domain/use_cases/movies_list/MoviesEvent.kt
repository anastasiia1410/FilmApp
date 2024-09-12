package com.example.filmapp.domain.use_cases.movies_list

import com.example.filmapp.domain.entity.Movie

sealed class MoviesEvent {
    object GetMovies : MoviesEvent()
    data class ShowMovies(val movies : List<Movie>) : MoviesEvent()
    data class Error(val message: String) : MoviesEvent()

}