package com.example.filmapp.domain.entity

import com.example.filmapp.domain.use_cases.detail_movie.DetailState

data class Movie(
    val id: Long,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val overview: String
) {
    companion object {
        fun initialContact(): DetailState {
            return DetailState(null)
        }
    }
}