package com.example.filmapp.domain.repository

import com.example.filmapp.domain.entity.Movie

interface NetworkRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovieDetail(id: Long): Movie
}