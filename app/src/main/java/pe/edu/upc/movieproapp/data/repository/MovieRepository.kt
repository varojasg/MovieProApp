package pe.edu.upc.movieproapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.movieproapp.data.remote.ApiConstants
import pe.edu.upc.movieproapp.data.remote.MovieService
import pe.edu.upc.movieproapp.data.local.FavoriteMovieDao
import pe.edu.upc.movieproapp.data.model.FavoriteMovieEntity
import pe.edu.upc.movieproapp.data.model.toMovie
import pe.edu.upc.movieproapp.domain.Movie

class MovieRepository(
    private val movieService: MovieService,
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun getPopularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val movieResponse = movieService.getPopularMovies(ApiConstants.API_KEY)
        if (!movieResponse.isSuccessful) throw Exception("No se pudieron cargar las películas")
        val movieList = movieResponse.body()?.results ?: emptyList()
        movieList.map { movieDto ->
            movieDto.toMovie(favoriteMovieDao.findFavorite(movieDto.id) != null)
        }
    }

    suspend fun getFavoriteMovies(): List<FavoriteMovieEntity> = withContext(Dispatchers.IO) {
        favoriteMovieDao.getFavorites()
    }

    suspend fun addFavorite(movie: Movie) = withContext(Dispatchers.IO) {
        favoriteMovieDao.insertFavorite(FavoriteMovieEntity(movie.id, movie.title, movie.overview))
    }

    suspend fun removeFavorite(favoriteMovie: FavoriteMovieEntity) = withContext(Dispatchers.IO) {
        favoriteMovieDao.deleteFavorite(favoriteMovie.id)
    }
}
