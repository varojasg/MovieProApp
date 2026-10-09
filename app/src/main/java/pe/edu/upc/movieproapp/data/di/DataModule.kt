package pe.edu.upc.movieproapp.data.di

import android.content.Context
import androidx.room.Room
import pe.edu.upc.movieproapp.data.local.AppDatabase
import pe.edu.upc.movieproapp.data.remote.ApiConstants
import pe.edu.upc.movieproapp.data.remote.MovieService
import pe.edu.upc.movieproapp.data.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Reúne las dependencias de datos, como en MealsCompose.
object DataModule {
    private var database: AppDatabase? = null

    fun getMovieService(): MovieService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

    fun getAppDatabase(context: Context): AppDatabase {
        return database ?: Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "moviepro_database"
        ).build().also { database = it }
    }

    fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(getMovieService(), getAppDatabase(context).getFavoriteMovieDao())
    }
}
