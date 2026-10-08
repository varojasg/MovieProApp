package pe.edu.upc.movieproapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.movieproapp.common.Constants
import pe.edu.upc.movieproapp.data.MovieService
import pe.edu.upc.movieproapp.data.local.FavoriteMovieDao
import pe.edu.upc.movieproapp.data.local.FavoriteMovieEntity
import pe.edu.upc.movieproapp.domain.model.Movie

class MovieRepository(
    private val movieService: MovieService,
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun getPopularMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val movieResponse = movieService.getPopularMovies(Constants.API_KEY)
        if (!movieResponse.isSuccessful) throw Exception("No se pudieron cargar las películas")
        val movieList = movieResponse.body()?.results ?: emptyList()
        movieList.map { movieDto ->
            Movie(movieDto.id, movieDto.title, movieDto.overview, movieDto.popularity,
                favoriteMovieDao.findFavorite(movieDto.id) != null)
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
