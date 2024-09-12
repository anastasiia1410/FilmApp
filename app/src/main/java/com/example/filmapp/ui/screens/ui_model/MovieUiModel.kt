package com.example.filmapp.ui.screens.ui_model

import com.example.filmapp.domain.entity.Movie

data class MovieUiModel (
    val id: Long,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
)

fun Movie.toMovieUiModel() : MovieUiModel {
    return MovieUiModel(
        id = this.id,
        posterPath = this.posterPath,
        title = this.title,
        voteAverage = this.voteAverage,
        overview = this.overview
    )
}