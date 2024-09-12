package com.example.filmapp.ui.screens.detail.detail_ui_state

import com.example.filmapp.domain.use_cases.detail_movie.DetailState
import com.example.filmapp.ui.screens.ui_model.MovieUiModel
import com.example.filmapp.ui.screens.ui_model.toMovieUiModel

class DetailUiState(val movie: MovieUiModel?) {
    companion object {
        fun initialMovieUi(): DetailUiState {
            return DetailUiState(null)
        }
    }
}

fun DetailState.toUiState(): DetailUiState {
    return DetailUiState(
        movie = this.movie?.toMovieUiModel()
    )
}