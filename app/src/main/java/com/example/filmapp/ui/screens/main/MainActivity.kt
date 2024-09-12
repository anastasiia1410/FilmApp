package com.example.filmapp.ui.screens.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filmapp.core.Router
import com.example.filmapp.ui.screens.detail.MovieDetailScreen
import com.example.filmapp.ui.screens.detail.MovieDetailViewModel
import com.example.filmapp.ui.screens.movie_list.MoviesListScreen
import com.example.filmapp.ui.theme.FilmAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var router: Router
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmAppTheme {
                MoviesApp(startScreen = Screen.MoviesScreen.route, router = router)
            }
        }
    }

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun movieDetailViewModelFactory(): MovieDetailViewModel.Factory
    }

    @Composable
    fun MoviesApp(startScreen: String, router: Router) {
        Surface {
            val navController = rememberNavController()
            router.attach(navController)
            MainNavHost(navController = navController, startScreen = startScreen)
        }
    }

    @Composable
    fun MainNavHost(navController: NavHostController, startScreen: String) {
        NavHost(
            navController = navController,
            startDestination = startScreen
        ) {
            composable(route = Screen.MoviesScreen.route) {
                MoviesListScreen()

            }
            composable(route = Screen.MovieDetailScreen.route + "/{id}") { backStackEntry ->
                val idString = backStackEntry.arguments?.getString("id")
                val id = idString?.toLongOrNull()
                id?.let {
                    MovieDetailScreen(noteDetailViewModel(id = id))
                }
            }
        }
    }
}

@Composable
fun noteDetailViewModel(id: Long): MovieDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).movieDetailViewModelFactory()

    return viewModel(
        factory = MovieDetailViewModel.provideUserDetailViewModelFactory(
            factory,
            id
        )
    )
}
