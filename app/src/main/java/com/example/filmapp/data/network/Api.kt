package com.example.filmapp.data.network

import com.example.filmapp.data.network.entity.MovieNetwork
import com.example.filmapp.data.network.entity.response.GetFilmsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("3/movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
    ): GetFilmsResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieNetwork

    companion object {
        private const val API_KEY = "efa443b03883377e3764ccdee9af39b0"
    }
}