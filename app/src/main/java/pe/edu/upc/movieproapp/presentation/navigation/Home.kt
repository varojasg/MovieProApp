package pe.edu.upc.movieproapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.movieproapp.presentation.view.FavoriteMoviesView
import pe.edu.upc.movieproapp.presentation.view.HomeView
import pe.edu.upc.movieproapp.presentation.view.PopularMoviesView
import pe.edu.upc.movieproapp.presentation.viewmodel.MovieListViewModel

@Composable
fun Home(movieListViewModel: MovieListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeView(
                onPopularClick = { navController.navigate("popular") },
                onFavoritesClick = { navController.navigate("favorites") }
            )
        }
        composable("popular") {
            PopularMoviesView(movieListViewModel, onBackClick = { navController.popBackStack() })
        }
        composable("favorites") {
            FavoriteMoviesView(movieListViewModel, onBackClick = { navController.popBackStack() })
        }
    }
}
