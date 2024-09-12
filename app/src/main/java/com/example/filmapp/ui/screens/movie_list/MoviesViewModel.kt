package com.example.filmapp.ui.screens.movie_list

import androidx.lifecycle.viewModelScope
import com.example.filmapp.core.BaseViewModel
import com.example.filmapp.core.Router
import com.example.filmapp.domain.use_cases.movies_list.GetMoviesUseCase
import com.example.filmapp.domain.use_cases.movies_list.MoviesEvent
import com.example.filmapp.domain.use_cases.movies_list.MoviesReducer
import com.example.filmapp.domain.use_cases.movies_list.MoviesState
import com.example.filmapp.ui.screens.main.Screen
import com.example.filmapp.ui.screens.movie_list.ui_state.MoviesUiState
import com.example.filmapp.ui.screens.movie_list.ui_state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val router: Router,
    getMoviesUseCase: GetMoviesUseCase,
) : BaseViewModel<MoviesEvent, MoviesState>(
    useCases = listOf(getMoviesUseCase),
    reducer = MoviesReducer(),
    initialState = MoviesState(emptyList())
) {
    val uiState = state.map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = MoviesUiState(
                movies = persistentListOf()
            )
        )

    fun getMovies(){
        handleEvent(MoviesEvent.GetMovies)
    }

    fun moveToDetailMovieScreen(id : Long){
        router.navigate(Screen.MovieDetailScreen.route + "/${id}")
    }


}