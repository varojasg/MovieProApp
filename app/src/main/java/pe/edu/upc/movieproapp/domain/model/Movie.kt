package pe.edu.upc.movieproapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val isFavorite: Boolean
)
