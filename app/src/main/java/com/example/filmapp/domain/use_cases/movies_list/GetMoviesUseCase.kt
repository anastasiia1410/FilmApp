package com.example.filmapp.domain.use_cases.movies_list

import com.example.filmapp.core.UseCase
import com.example.filmapp.domain.repository.NetworkRepository

class GetMoviesUseCase(private val networkRepository: NetworkRepository) :
    UseCase<MoviesEvent, MoviesState> {
    override fun canHandle(event: MoviesEvent): Boolean {
        return event is MoviesEvent.GetMovies
    }

    override suspend fun invoke(event: MoviesEvent, state: MoviesState): MoviesEvent {
        return ((event as? MoviesEvent.GetMovies))?.let {
            try {
                val movies = networkRepository.getMovies()
                return MoviesEvent.ShowMovies(movies)
            } catch (e: Exception) {
                return MoviesEvent.Error("something was wrong")
            }
        } ?: MoviesEvent.Error("wrong event type: $event")
    }
}