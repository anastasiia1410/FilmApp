package com.example.filmapp.domain.use_cases.detail_movie

import com.example.filmapp.core.Reducer

class DetailReducer : Reducer<DetailEvent, DetailState> {
    override fun reduce(event: DetailEvent, state: DetailState): DetailState {
        return when (event) {
            is DetailEvent.ShowMovie -> state.copy(movie = event.movie)
            is DetailEvent.Error, is DetailEvent.GetMovie -> state
            DetailEvent.None -> state
        }
    }
}