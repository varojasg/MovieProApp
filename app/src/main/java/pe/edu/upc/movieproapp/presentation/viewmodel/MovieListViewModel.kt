package pe.edu.upc.movieproapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.edu.upc.movieproapp.data.model.FavoriteMovieEntity
import pe.edu.upc.movieproapp.data.repository.MovieRepository
import pe.edu.upc.movieproapp.domain.Movie

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _popularMovies = mutableStateOf<List<Movie>>(emptyList())
    val popularMovies: State<List<Movie>> get() = _popularMovies

    private val _favoriteMovies = mutableStateOf<List<FavoriteMovieEntity>>(emptyList())
    val favoriteMovies: State<List<FavoriteMovieEntity>> get() = _favoriteMovies

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> get() = _errorMessage

    fun loadPopularMovies() {
        _isLoading.value = true
        _errorMessage.value = ""
        viewModelScope.launch {
            try {
                _popularMovies.value = movieRepository.getPopularMovies()
            } catch (exception: Exception) {
                _errorMessage.value = "No se pudieron cargar las películas. Revisa tu conexión."
            }
            _isLoading.value = false
        }
    }

    fun loadFavoriteMovies() {
        viewModelScope.launch {
            _favoriteMovies.value = movieRepository.getFavoriteMovies()
        }
    }

    fun addFavorite(movie: Movie) {
        if (movie.isFavorite) return
        viewModelScope.launch {
            movieRepository.addFavorite(movie)
            _popularMovies.value = _popularMovies.value.map { popularMovie ->
                if (popularMovie.id == movie.id) popularMovie.copy(isFavorite = true) else popularMovie
            }
            _favoriteMovies.value = movieRepository.getFavoriteMovies()
        }
    }

    fun deleteFavorite(favoriteMovie: FavoriteMovieEntity) {
        viewModelScope.launch {
            movieRepository.removeFavorite(favoriteMovie)
            _favoriteMovies.value = movieRepository.getFavoriteMovies()
            _popularMovies.value = _popularMovies.value.map { popularMovie ->
                if (popularMovie.id == favoriteMovie.id) popularMovie.copy(isFavorite = false) else popularMovie
            }
        }
    }
}
