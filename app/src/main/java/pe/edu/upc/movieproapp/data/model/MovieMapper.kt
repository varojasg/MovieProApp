package pe.edu.upc.movieproapp.data.model

import pe.edu.upc.movieproapp.domain.Movie

// Convierte la respuesta de la API al modelo que usa la interfaz.
fun MovieDto.toMovie(isFavorite: Boolean): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        popularity = popularity,
        isFavorite = isFavorite
    )
}
