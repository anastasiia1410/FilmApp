package com.example.filmapp.di

import com.example.filmapp.core.Router
import com.example.filmapp.core.RouterImpl
import com.example.filmapp.data.network.Api
import com.example.filmapp.data.network.NetworkRepositoryImpl
import com.example.filmapp.domain.repository.NetworkRepository
import com.example.filmapp.domain.use_cases.detail_movie.GetMovieDetailUseCase
import com.example.filmapp.domain.use_cases.movies_list.GetMoviesUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface Binds {

    @Binds
    @Singleton
    fun provideRouter(impl: RouterImpl): Router

    @Binds
    @Singleton
    fun bindNetworkRepository(impl: NetworkRepositoryImpl): NetworkRepository
}

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(networkRepository: NetworkRepository): GetMoviesUseCase {
        return GetMoviesUseCase(networkRepository)
    }

    @Provides
    fun provideGetDetailMovieUseCase(networkRepository: NetworkRepository): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(networkRepository)
    }

}