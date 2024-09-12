package com.example.filmapp.domain.use_cases.detail_movie

import com.example.filmapp.domain.entity.Movie

sealed class DetailEvent {
    data class GetMovie(val id: Long) : DetailEvent()
    data class ShowMovie(val movie: Movie) : DetailEvent()
    object None : DetailEvent()
    data class Error(val error: String) : DetailEvent()
}