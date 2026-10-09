package pe.edu.upc.movieproapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pe.edu.upc.movieproapp.presentation.di.PresentationModule
import pe.edu.upc.movieproapp.presentation.navigation.Home
import pe.edu.upc.movieproapp.ui.theme.MovieProAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val movieListViewModel = PresentationModule.getMovieListViewModel(applicationContext)

        setContent {
            MovieProAppTheme {
                Home(movieListViewModel)
            }
        }
    }
}
