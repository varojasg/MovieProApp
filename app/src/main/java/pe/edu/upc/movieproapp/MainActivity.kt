package pe.edu.upc.movieproapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.room.Room
import pe.edu.upc.movieproapp.common.Constants
import pe.edu.upc.movieproapp.data.MovieService
import pe.edu.upc.movieproapp.data.local.AppDatabase
import pe.edu.upc.movieproapp.data.repository.MovieRepository
import pe.edu.upc.movieproapp.presentation.home.HomeScreen
import pe.edu.upc.movieproapp.presentation.movie.FavoritesScreen
import pe.edu.upc.movieproapp.presentation.movie.PopularScreen
import pe.edu.upc.movieproapp.presentation.movie.MovieViewModel
import pe.edu.upc.movieproapp.ui.theme.MovieProAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val movieService = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)

        val favoriteMovieDao = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "moviepro_database"
        ).build().getFavoriteMovieDao()

        val movieViewModel = MovieViewModel(MovieRepository(movieService, favoriteMovieDao))

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieProAppTheme {
                var currentScreen by remember { mutableStateOf("home") }
                when (currentScreen) {
                    "popular" -> PopularScreen(movieViewModel) { currentScreen = "home" }
                    "favorites" -> FavoritesScreen(movieViewModel) { currentScreen = "home" }
                    else -> HomeScreen(
                        onPopularClick = { currentScreen = "popular" },
                        onFavoritesClick = { currentScreen = "favorites" }
                    )
                }
                androidx.activity.compose.BackHandler(enabled = currentScreen != "home") {
                    currentScreen = "home"
                }
            }
        }
    }
}
