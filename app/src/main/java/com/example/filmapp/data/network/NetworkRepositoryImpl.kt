package com.example.filmapp.data.network

import com.example.filmapp.data.network.entity.toMovie
import com.example.filmapp.domain.entity.Movie
import com.example.filmapp.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: Api) : NetworkRepository {
    override suspend fun getMovies(): List<Movie> {
        return api.getMovies().results.map { it.toMovie() }
    }

    override suspend fun getMovieDetail(id: Long): Movie {
        return api.getMovieDetails(id).toMovie()
    }
}