package pe.edu.upc.movieproapp.presentation.view

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
import androidx.compose.material3.CircularProgressIndicator
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
import pe.edu.upc.movieproapp.presentation.view.ScreenHeader

@Composable
fun PopularMoviesView(movieViewModel: MovieListViewModel, onBackClick: () -> Unit) {
    LaunchedEffect(Unit) { movieViewModel.loadPopularMovies() }

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            ScreenHeader("PELÍCULAS POPULARES", onBackClick)
            if (movieViewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            if (movieViewModel.errorMessage.value.isNotEmpty()) {
                Text(
                    movieViewModel.errorMessage.value,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(movieViewModel.popularMovies.value, key = { movie -> movie.id }) { popularMovie ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(popularMovie.title, style = MaterialTheme.typography.titleMedium)
                                Text(popularMovie.overview.ifBlank { "Sin sinopsis disponible" })
                                Text(
                                    "Popularidad: ${popularMovie.popularity}",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            IconButton(
                                enabled = !popularMovie.isFavorite,
                                onClick = {
                                    movieViewModel.addFavorite(popularMovie)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_favorite),
                                    contentDescription = if (popularMovie.isFavorite) "Ya está en favoritos" else "Guardar en favoritos",
                                    tint = if (popularMovie.isFavorite) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
