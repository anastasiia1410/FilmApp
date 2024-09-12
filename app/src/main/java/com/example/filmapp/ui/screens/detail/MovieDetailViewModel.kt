package com.example.filmapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filmapp.core.BaseViewModel
import com.example.filmapp.domain.entity.Movie
import com.example.filmapp.domain.use_cases.detail_movie.DetailEvent
import com.example.filmapp.domain.use_cases.detail_movie.DetailReducer
import com.example.filmapp.domain.use_cases.detail_movie.DetailState
import com.example.filmapp.domain.use_cases.detail_movie.GetMovieDetailUseCase
import com.example.filmapp.ui.screens.detail.detail_ui_state.DetailUiState
import com.example.filmapp.ui.screens.detail.detail_ui_state.toUiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieDetailViewModel @AssistedInject constructor(
    getMovieDetailUseCase: GetMovieDetailUseCase,
    @Assisted private val id: Long,
) : BaseViewModel<DetailEvent, DetailState>(
    useCases = listOf(getMovieDetailUseCase),
    reducer = DetailReducer(),
    initialState = Movie.initialContact()
) {

    val uiState: StateFlow<DetailUiState> = state.map { it.toUiState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = DetailUiState.initialMovieUi()
    )

    init {
        getUser(id)
    }

    private fun getUser(id: Long) {
        handleEvent(DetailEvent.GetMovie(id))
    }

    @AssistedFactory
    interface Factory {
        fun create(
            id: Long,
        ): MovieDetailViewModel
    }

    companion object {
        fun provideUserDetailViewModelFactory(
            factory: Factory,
            id: Long,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(id) as T
                }
            }
        }
    }
}