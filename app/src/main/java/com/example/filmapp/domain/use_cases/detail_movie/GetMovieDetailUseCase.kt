package com.example.filmapp.domain.use_cases.detail_movie

import com.example.filmapp.core.UseCase
import com.example.filmapp.domain.repository.NetworkRepository

class GetMovieDetailUseCase(private val networkRepository: NetworkRepository) :
    UseCase<DetailEvent, DetailState> {
    override fun canHandle(event: DetailEvent): Boolean {
        return event is DetailEvent.GetMovie
    }

    override suspend fun invoke(event: DetailEvent, state: DetailState): DetailEvent {
        return ((event as? DetailEvent.GetMovie))?.let {
            try {
                val movie = networkRepository.getMovieDetail(event.id)
                return DetailEvent.ShowMovie(movie)
            } catch (e: Exception) {
                return DetailEvent.Error("something was wrong")
            }
        } ?: return DetailEvent.Error("wrong event type: $event")
    }
}