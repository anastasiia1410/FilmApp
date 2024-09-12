package com.example.filmapp.domain.entity

data class Movie(
    val id : Long,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val overview: String,
)