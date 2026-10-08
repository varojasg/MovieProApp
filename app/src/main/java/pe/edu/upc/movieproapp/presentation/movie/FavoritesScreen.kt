package pe.edu.upc.movieproapp.presentation.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.edu.upc.movieproapp.R
import pe.edu.upc.movieproapp.data.local.FavoriteMovieEntity
import pe.edu.upc.movieproapp.presentation.shared.ScreenHeader

@Composable
fun FavoritesScreen(movieViewModel: MovieViewModel, onBackClick: () -> Unit) {
    LaunchedEffect(Unit) { movieViewModel.loadFavoriteMovies() }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            ScreenHeader("MIS FAVORITOS", onBackClick)
            if (movieViewModel.favoriteMovies.value.isEmpty()) {
                Text("Todavía no tienes películas favoritas.", modifier = Modifier.padding(16.dp))
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(movieViewModel.favoriteMovies.value, key = { movie -> movie.id }) { favoriteMovie ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(favoriteMovie.title, style = MaterialTheme.typography.titleMedium)
                                Text(favoriteMovie.overview.ifBlank { "Sin sinopsis disponible" })
                            }
                            IconButton(onClick = { movieViewModel.deleteFavorite(favoriteMovie) }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_favorite),
                                    contentDescription = "Eliminar ${favoriteMovie.title} de favoritos",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
