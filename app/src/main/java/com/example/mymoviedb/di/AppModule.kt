package com.example.mymoviedb.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mymoviedb.BuildConfig
import com.example.mymoviedb.data_sources.GenreDatabase
import com.example.mymoviedb.data_sources.GenresDAO
import com.example.mymoviedb.network.GenreApi
import com.example.mymoviedb.network.ListDetailApi
import com.example.mymoviedb.repository.FilmRemoteDataSource
import com.example.mymoviedb.network.MovieDBApi
import com.example.mymoviedb.repository.ExploreRepository
import com.example.mymoviedb.repository.FilmRepository
import com.example.mymoviedb.repository.ListDetailRepository
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.ListGenreRemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + BuildConfig.API_KEY)
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MovieDBApi = retrofit.create(MovieDBApi::class.java)

    @Singleton
    @Provides
    fun provideGenreApiService(retrofit: Retrofit): GenreApi = retrofit.create(GenreApi::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(movieDBApi: MovieDBApi): FilmRemoteDataSource =
        FilmRemoteDataSource(movieDBApi)

//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext appContext: Context) =
//        Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()
//
//    @Singleton
//    @Provides
//    fun provideCountryDao(db: AppDatabase) = db.countryDao()

    @Singleton
    @Provides
    fun provideHomePreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Const.HOME_PREF, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providesHomeRepository(
        filmRemoteDataSource: FilmRemoteDataSource, homePreferences: SharedPreferences,
    ) = FilmRepository(filmRemoteDataSource, homePreferences)

    @Singleton
    @Provides
    fun provideGenreDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            GenreDatabase::class.java, "Genres Database"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideGenreDao(db: GenreDatabase) = db.genresDao()

    @Singleton
    @Provides
    fun provideExploreRepository(
        api: GenreApi, localDataSource: GenresDAO, mapper: ListGenreRemoteMapper,
    ) = ExploreRepository(api, localDataSource, mapper)

    @Singleton
    @Provides
    fun provideListGenreMapper() = ListGenreRemoteMapper()

    @Singleton
    @Provides
    fun provideListDetailApiService(retrofit: Retrofit): ListDetailApi =
        retrofit.create(ListDetailApi::class.java)

    @Singleton
    @Provides
    fun provideListDetailRepository(api: ListDetailApi) = ListDetailRepository(api)

}
