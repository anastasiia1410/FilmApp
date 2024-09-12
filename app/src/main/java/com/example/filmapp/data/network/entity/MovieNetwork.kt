package com.example.filmapp.data.network.entity

import com.example.filmapp.domain.entity.Movie
import com.google.gson.annotations.SerializedName

data class MovieNetwork(
    val id: Long,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overview: String,
)

fun MovieNetwork.toMovie(): Movie {
    return Movie(
        id = this.id,
        posterPath = this.posterPath,
        title = this.title,
        voteAverage = this.voteAverage,
        overview = this.overview
    )
}