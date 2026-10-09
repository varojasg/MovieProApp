package pe.edu.upc.movieproapp.data.model

data class MovieDto(val id: Int, val title: String, val overview: String, val popularity: Double)

data class MovieResponse(val results: List<MovieDto>)
