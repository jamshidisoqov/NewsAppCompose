package uz.gita.news_app_compose.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.news_app_compose.data.remote.api.NewsApi
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/10/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val baseUrl = "https://newsapi.org/v2/"

    @[Provides Singleton]
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton]
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

}