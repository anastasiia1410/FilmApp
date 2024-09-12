package com.example.filmapp.ui.screens.movie_list.ui_state

import com.example.filmapp.domain.use_cases.movies_list.MoviesState
import com.example.filmapp.ui.screens.ui_model.MovieUiModel
import com.example.filmapp.ui.screens.ui_model.toMovieUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList


data class MoviesUiState(val movies: ImmutableList<MovieUiModel>)

fun MoviesState.toUiState(): MoviesUiState {
    return MoviesUiState(
        movies = this.movies.map { it.toMovieUiModel() }.toImmutableList()
    )
}