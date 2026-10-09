package pe.edu.upc.movieproapp.presentation.di

import android.content.Context
import pe.edu.upc.movieproapp.data.di.DataModule
import pe.edu.upc.movieproapp.presentation.viewmodel.MovieListViewModel

// Proporciona el ViewModel con su repositorio.
object PresentationModule {
    fun getMovieListViewModel(context: Context): MovieListViewModel {
        return MovieListViewModel(DataModule.getMovieRepository(context))
    }
}
