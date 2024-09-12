package com.example.filmapp.domain.use_cases.movies_list

import com.example.filmapp.core.Reducer

class MoviesReducer : Reducer<MoviesEvent, MoviesState> {
    override fun reduce(event: MoviesEvent, state: MoviesState): MoviesState {
        return when(event){
            is MoviesEvent.Error -> state
            MoviesEvent.GetMovies -> state
            is MoviesEvent.ShowMovies -> state.copy(movies = event.movies)
        }
    }
}